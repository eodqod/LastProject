package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.ReservationService;
import kr.human.camping.service.RoomService;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.RoomVO;


@Controller
public class ReservationController {

	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private RoomService roomService;
	
	// 예약페이지
	@RequestMapping(value ="/reservation", method = {RequestMethod.GET})
		public String selectList(
				@RequestParam("roomidx") int roomidx, // 내가 선택한 방의 roomidx값
//				@RequestParam("starday") String starday, // 입실일
//				@RequestParam("endday") String endday, // 퇴실일
//				HttpServletRequest request, // 세션에서 값을 가져오는 방법	
//				HttpSession session,  // 세션에서 값을 가져오는 방법	
				Model model){
		int idx = roomService.companyidx(roomidx);
		RoomVO rvo = roomService.selectRoom(roomidx);
		CompanyVO cvo = reservationService.selectCompany(idx);
		 
		
//		 String id = request.getParameter("id");
//		 String sessionId = (String) session.getAttribute("id");// 세션에서 값을 가져오는 방법	

		model.addAttribute("rvo",rvo);
		model.addAttribute("cvo",cvo);
		return "reservation/reservation";
	}
	
	
	
}
