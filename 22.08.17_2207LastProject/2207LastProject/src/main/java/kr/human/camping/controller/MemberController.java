package kr.human.camping.controller;


import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import kr.human.camping.email.MailService;
import kr.human.camping.service.MemberService;
import kr.human.camping.service.PasswordService;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.UserVo;
import lombok.RequiredArgsConstructor;

@Controller("MemberController")
public class MemberController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MailService mailService;
	// 로그인
	// 회원정보가져오기
	// 로그아웃
	// 가입메일 전송하기
	// 임시비밀번호 발급
	// 
	
	@Bean
	public SpringSecurityDialect securityDialect(){
		return new SpringSecurityDialect();
	}
	
	// 로그인
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout, Model model ) {
		if(error!=null) model.addAttribute("error","error");
		if(logout!=null) model.addAttribute("logout","logout");
		return "login";
	}
	
	// 회원가입
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insertMemeber(@ModelAttribute MemberVO memberVO, Model model) {
		System.out.println(memberVO);
		memberService.MemberInsert(memberVO);
		model.addAttribute("memberVO",memberVO);
		
		return "welcome";
	} 
	
	// IdOverlap
	@RequestMapping(value = "/IdOverlap", method = RequestMethod.GET)
	@ResponseBody
	public int IdCheck(@RequestParam("id") String id, Model model) {
		System.out.println(id);
		int result = 0;
		result = memberService.IDOverlap(id);
		return result;
	}
	
	// EmailOverlap
	@RequestMapping(value = "/EmailOverlap", method = RequestMethod.GET)
	@ResponseBody
	public int EmailCheck(@ModelAttribute String email, Model model) {
		System.out.println(email);
		int result = 0;
		result = memberService.IDOverlap(email);
		return result;
	}
	
	/**
     * 로그인 실패 폼
     * @return
     */
	@RequestMapping("/access_denied")
    public String accessDenied() {
        return "access_denied";
    }
	
	// 로그인 성공 폼
	@RequestMapping("/user_access")
    public String userAccess(Model model, Authentication authentication) {
        //Authentication 객체를 통해 유저 정보를 가져올 수 있다.
        UserVo userVo = (UserVo) authentication.getPrincipal();  //userDetail 객체를 가져옴
        model.addAttribute("info", userVo.getId() +"의 "+ userVo.getUserName()+ "님");      //유저 아이디
        return "user_access";
    }
	
	
	@RequestMapping("/EmailCheck")
    public String welcome() {
      
        return "Emailwelcome";
    }
	
	// 이메일 인증 
	// 문제: 이메일에서 localhost로 링크가 안걸림
	@RequestMapping("/confilm")
    public String confilm(@RequestParam("id") String id) {
		// 해당 id를 인증하여 role을 user로 변환해준다.
		System.out.println(id);
		memberService.updateAccess(id);
        return "redirect:/login";
    }
	
	// 아이디 찾기
	@RequestMapping("/findID")
	public String findID(@ModelAttribute Model model) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = memberService.findID((String)model.getAttribute("email"));
		model.addAttribute("id",map.get("id"));
		model.addAttribute("name",map.get("name"));
		return "login";
	}
	
	// 비밀번호 찾기
	// map에는 id, email, name 을 넘겨준다. 
	@RequestMapping("/findPassword")
	public String findPassword(@ModelAttribute HashMap<String, String> map) {
		// 새로운 비밀번호 생성
		String newPassword = PasswordService.makeNewPassword();
		// 새비밀번호를 넣어서 서비스로 GO!
		map.put("password", newPassword);
		memberService.changePassword(map);
		// 비밀번호 해당 email로 쏴주고
		mailService.sendEmail(map);
		return "login";
	}
	
	// 내 정보 수정하기
	@RequestMapping("/updateInfo")
	public String updateInfo() {
		
		return "updateInfo";
	}
	
	@RequestMapping("/findpage")
	public String findpage() {
		
		return "findpage";
	}
	
	
	
}
