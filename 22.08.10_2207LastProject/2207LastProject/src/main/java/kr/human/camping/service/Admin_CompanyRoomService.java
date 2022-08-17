package kr.human.camping.service;

import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.RoomVO;

public interface Admin_CompanyRoomService {
	// 목록보기
	PagingVO<RoomVO> selectRoomList(int currentPage, int pageSize, int blockSize);
	// 1개 내용보기
	RoomVO selectByRoomIdx(int roomidx);
	// 저장하기
	boolean insert(RoomVO roomVO);
	// 수정하기
	boolean update(RoomVO roomVO);
	// 삭제하기
	boolean delete(RoomVO roomVO);

}
