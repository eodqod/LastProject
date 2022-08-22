package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.ReservationService;
import kr.human.camping.service.RoomService;
import kr.human.camping.vo.Comm2VO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.ReservationVO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
//				HttpServletRequest request, // 세션에서 값을 가져오는 방법	
//				HttpSession session,  // 세션에서 값을 가져오는 방법	
				Model model){
		int idx = roomService.companyidx(roomidx);
		RoomVO rvo = roomService.selectRoom(roomidx);
		CompanyVO cvo = reservationService.selectCompany(idx);
				
//		 String id = request.getParameter("id");
//		 String sessionId = (String) session.getAttribute("id");// 세션에서 값을 가져오는 방법	

		model.addAttribute("rvo",rvo);
		model.addAttribute("roomidx",roomidx);
		model.addAttribute("cvo",cvo);
		return "reservation/reservation";
	}
	
	// 저장/삭제  수정기능은 없습니다.
		@RequestMapping(value = "reservationupdate", method = RequestMethod.GET)
		public String updateGet() {
			
			return "redirect:/roomList";
		}
		
		@RequestMapping(value = "reservationupdate", method = RequestMethod.POST)
		public String updatePost(@ModelAttribute Comm2VO comm2VO, @ModelAttribute ReservationVO reservationVO) {
			boolean result = false;
			log.info("updatePost : " + reservationVO);
			log.info("updatePost : " + comm2VO);
			switch (comm2VO.getMode()) {
			case "insert":
				result = reservationService.insertReservation(reservationVO);
				log.info("insertReservation 실행결과 : " + result);
				break;
			case "delete":
				result = reservationService.deleteReservation(reservationVO);
				log.info("deleteReservation 실행결과 : " + result);
				break;
			}
			return "redirect:/roomList";
		}
	
}
