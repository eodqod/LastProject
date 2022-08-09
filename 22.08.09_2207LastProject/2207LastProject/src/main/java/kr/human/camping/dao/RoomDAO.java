package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.RoomVO;

@Mapper
public interface RoomDAO {
	// 전체 개수 구하기
	int selectRoomnCount() throws SQLException;
	// 방 1개 보기
	RoomVO selectRoom(int roomidx) throws SQLException;
	// 방 추가
	void insertRoom(RoomVO roomVO) throws SQLException;
	// 방 수정
	void updatetRoom(RoomVO roomVO) throws SQLException;
	// 방 삭제
	void deletetRoom(int roomidx) throws SQLException;
	// 방 r_check 변경하기
	void updateCheck(int roomidx) throws SQLException;
	// 방 한페이지 보기
	List<RoomVO> selectRoomList(HashMap<String, Integer> map) throws SQLException;
}
