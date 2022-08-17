package kr.human.camping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.Admin_CompanyRoomService;
import kr.human.camping.service.Admin_CompanyService;
import kr.human.camping.service.FileBoardService;
import kr.human.camping.vo.CommVO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class Admin_CompanyRoomController {

	@Autowired
	private Admin_CompanyRoomService admin_CompanyRoomService;
	
	// 업체 방 전체 보기
	@RequestMapping("/CompanyRoomList")
	public String selectRoomList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			Model model 
			){
		PagingVO<RoomVO> pagingVO = admin_CompanyRoomService.selectRoomList(p, s, b);
		model.addAttribute("pv", pagingVO);
		log.info("pagingVO : " + pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomList";
	}
	
	// 1개 내용보기
	@RequestMapping("/CompanyRoomView")
	public String selectRoomIdx(@RequestParam("roomidx") int roomidx, Model model) {
		RoomVO vo = admin_CompanyRoomService.selectByRoomIdx(roomidx);
		model.addAttribute("vo", vo);
		return "admin/Company/CompanyRoomView";
	}
	
	// 새글쓰기
	@RequestMapping("/CompanyRoomInsert")
	public String insert(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			Model model) {
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomInsert";
	}

	// 수정하기
	@RequestMapping("/CompanyRoomUpdate")
	public String selectByIdx(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam("roomidx") int roomidx, Model model) {
		RoomVO vo = admin_CompanyRoomService.selectByRoomIdx(roomidx);
		model.addAttribute("vo", vo);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomUpdate";
	}
	
	// 삭제하기
	@RequestMapping("/CompanyRoomDelete")
	public String delete(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "5") int s,
			@RequestParam(required = false, defaultValue = "5") int b,
			@RequestParam("roomidx") int roomidx, Model model) {
		RoomVO vo = admin_CompanyRoomService.selectByRoomIdx(roomidx);
		model.addAttribute("vo", vo);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "admin/Company/CompanyRoomDelete";
	}

	// 저장/수정/삭제
	@RequestMapping(value = "/CompanyRoomUpdateOk", method = RequestMethod.GET)
	public String updateGet() {
		
		return "redirect:/CompanyRoomList";
	}
	@RequestMapping(value = "/CompanyRoomUpdateOk", method = RequestMethod.POST)
	public String updateCompany(@ModelAttribute CommVO commVO, @ModelAttribute RoomVO roomVO) {
		boolean result = false;
		log.info("updateCompanyRoom : " + roomVO);
		log.info("updateCompanyRoom : " + commVO);
		switch (commVO.getMode()) {
		case 1:
			result = admin_CompanyRoomService.insert(roomVO);
			log.info("insert 실행결과 : " + result);
			break;
		case 2:
			result = admin_CompanyRoomService.update(roomVO);
			log.info("update 실행결과 : " + result);
			break;
		case 3:
			result = admin_CompanyRoomService.delete(roomVO);
			log.info("delete 실행결과 : " + result);
			break;
		}
		return "redirect:/CompanyRoomList";
	}

}
