package kr.human.camping.service;

import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.ReservationVO;

public interface ReservationService {
	// 목록보기
	PagingVO<ReservationVO> selectReservationList(int currentPage, int pageSize, int blockSize);
	// 나의 예약 목록 보기
	PagingVO<ReservationVO> selectMyReservation(int currentPage, int pageSize, int blockSize, String id);
	// 예약 1개 보기
	ReservationVO selectReservation(int roonidx);
	// 예약하기
	boolean insertReservation(ReservationVO reservationVO);
	// 예약 취소하기
	boolean deleteReservation(ReservationVO reservationVO);
}
