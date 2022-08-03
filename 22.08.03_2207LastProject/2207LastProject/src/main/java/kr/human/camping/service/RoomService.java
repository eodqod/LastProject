package kr.human.camping.service;

import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.RoomVO;

public interface RoomService {
	// 방 목록보기
	PagingVO<RoomVO> selectRoomList(int currentPage, int pageSize, int blockSize);
	// 방 1개 보기
	RoomVO selectRoom(int roomidx);
	// 방 추가하기
	boolean insertRoom(RoomVO roomVO);
	// 방 수정하기
	boolean updateRoom(RoomVO roomVO);
	// 방 삭제하기
	boolean deleteRoom(RoomVO roomVO);
}
