package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.Admin_CompanyRoomService;
import kr.human.camping.service.Admin_CompanyService;
import kr.human.camping.service.Admin_MemberService;
import kr.human.camping.service.FileBoardService;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.SearchListPagingVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AdminController {

	@Autowired
	private Admin_CompanyService admin_CompanyService;

	@Autowired
	private Admin_CompanyRoomService admin_CompanyRoomService;

	@Autowired
	private Admin_MemberService admin_MemberService;

	@Autowired
	private FileBoardService fileBoardService;
	
	//관리자페이지 메인 GET방식 접근 차단
//	@RequestMapping(value = "/adminMain", method = RequestMethod.GET)
//	public String adminPageGet() {
//		
//		return "redirect:/adminMain";
//	}
	//, method = RequestMethod.POST 나중에 추가해야함
	@RequestMapping(value = "/adminMain")
	public String adminPagePost(Model model){
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "/admin/adminMain";
	}

}
