package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.CompanyService;
import kr.human.camping.service.ReservationService;
import kr.human.camping.service.RoomService;
import kr.human.camping.vo.CommVO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;

@Controller
public class ReservationController {

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private ReservationService reservationService;
	
	// 목록보기
	@RequestMapping("/reservationlist")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "3") int s,
			@RequestParam(required = false, defaultValue = "10") int b,
			Model model 
			){
		PagingVO<CompanyVO> CpagingVO = companyService.CompanyList(p, s, b);
		model.addAttribute("cpv", CpagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "reservationlist";
	}
	

}
