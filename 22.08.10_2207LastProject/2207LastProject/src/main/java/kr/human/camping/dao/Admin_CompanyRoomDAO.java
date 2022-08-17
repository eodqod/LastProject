package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.RoomVO;

@Mapper
public interface Admin_CompanyRoomDAO {
	// 업체 방 전체 수 구하기
	int selectCompanyRoomCount() throws SQLException;
	// 업체 방 1개 가져오기
	RoomVO selectByRoomIdx(int roomidx) throws SQLException;
	// 업체 방 1페이지 구하기
	List<RoomVO> selectRoomList(HashMap<String, Integer> Map) throws SQLException;
	// 업체 방 등록
	void insert(RoomVO roomVO) throws SQLException;
	// 업체 방 수정
	void update(RoomVO roomVO) throws SQLException;
	// 업체 방 삭제
	void delete(int idx) throws SQLException;
}
