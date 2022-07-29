package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.ReservationService;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.ReservationVO;

@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	// 목록보기
	@RequestMapping("/reservationlist")
	public String reservationlist(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "3") int s,
			@RequestParam(required = false, defaultValue = "10") int b,
			Model model 
			){
		PagingVO<ReservationVO> pagingVO = reservationService.selectReservationList(p, s, b);
		model.addAttribute("pv", pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "reservationlist";
	}
}
