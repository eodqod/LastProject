package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.Admin_CompanyDAO;
import kr.human.camping.dao.Admin_CompanyRoomDAO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.RoomVO;
import lombok.extern.slf4j.Slf4j;

@Service("Admin_CompanyRoomService")
@Transactional
@Slf4j
public class Admin_CompanyRoomServiceImpl  implements Admin_CompanyRoomService{

	@Autowired
	private Admin_CompanyRoomDAO admin_CompanyRoomDAO;

	@Override
	public PagingVO<RoomVO> selectRoomList(int currentPage, int pageSize, int blockSize) {
		PagingVO<RoomVO> pagingVO = null;
		try {
			int totalCount = admin_CompanyRoomDAO.selectCompanyRoomCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<RoomVO> list = admin_CompanyRoomDAO.selectRoomList(hashMap);
			log.info("쿼리결과 : {}",list.toString());
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

	@Override
	public RoomVO selectByRoomIdx(int roomidx) {
		RoomVO roomVO= null;
		try {
			roomVO = admin_CompanyRoomDAO.selectByRoomIdx(roomidx);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return roomVO;
	}

	@Override
	public boolean insert(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				admin_CompanyRoomDAO.insert(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				RoomVO dbVO = admin_CompanyRoomDAO.selectByRoomIdx(roomVO.getRoomidx()); // DB에서 원본 가져오기
				admin_CompanyRoomDAO.update(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@Override
	public boolean delete(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				RoomVO dbVO = admin_CompanyRoomDAO.selectByRoomIdx(roomVO.getRoomidx()); // DB에서 원본 가져오기
				admin_CompanyRoomDAO.delete(roomVO.getRoomidx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
