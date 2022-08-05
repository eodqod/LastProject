package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.ReservationDAO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.ReservationVO;

@Service("reservationService")
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	private ReservationDAO reservationDAO;

	@Override
	public PagingVO<ReservationVO> selectReservationList(int currentPage, int pageSize, int blockSize) {
		PagingVO<ReservationVO> pagingVO = null;
		try {
			int totalCount = reservationDAO.selectReservationCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("startNo",  Integer.toString(pagingVO.getStartNo()));
			hashMap.put("pageSize", Integer.toString(pagingVO.getPageSize()));
			List<ReservationVO> list = reservationDAO.selectReservationList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

	@Override
	public PagingVO<ReservationVO> selectMyReservation(int currentPage, int pageSize, int blockSize, String id) {
		PagingVO<ReservationVO> pagingVO = null;
		try {
			int totalCount = reservationDAO.selectReservationCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put("id", id);
			hashMap.put("startNo",  Integer.toString(pagingVO.getStartNo()));
			hashMap.put("pageSize", Integer.toString(pagingVO.getPageSize()));
			List<ReservationVO> list = reservationDAO.selectReservationList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

	@Override
	public ReservationVO selectReservation(int roomidx) {
		ReservationVO vo = null;
		try {
			vo = reservationDAO.selectReservation(roomidx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean insertReservation(ReservationVO reservationVO) {
		boolean result = false;
		if(reservationVO!=null) {
			try {
				reservationDAO.insertReservation(reservationVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteReservation(ReservationVO reservationVO) {
		boolean result = false;
		if(reservationVO!=null) {
			try {
				reservationDAO.deleteReservation(reservationVO.getRoomidx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
