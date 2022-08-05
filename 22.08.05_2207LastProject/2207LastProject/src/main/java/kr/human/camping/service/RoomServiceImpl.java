package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.RoomDAO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.RoomVO;

@Service("roomService")
public class RoomServiceImpl implements RoomService{
	
	@Autowired
	private RoomDAO roomDAO;
	
	@Override
	public PagingVO<RoomVO> selectRoomList(int currentPage, int pageSize, int blockSize) {
		PagingVO<RoomVO> pagingVO = null;
		try {
			int totalCount = roomDAO.selectRoomnCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("pageSize", pagingVO.getPageSize());
			List<RoomVO> list = roomDAO.selectRoomList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}
	
	@Override
	public RoomVO selectRoom(int roomidx) {
		RoomVO vo = null;
		try {
			vo = roomDAO.selectRoom(roomidx);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public boolean insertRoom(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				roomDAO.insertRoom(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean updateRoom(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				roomDAO.updatetRoom(roomVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean deleteRoom(RoomVO roomVO) {
		boolean result = false;
		if(roomVO!=null) {
			try {
				roomDAO.deletetRoom(roomVO.getRoomidx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}
