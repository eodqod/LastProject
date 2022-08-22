package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.TestVO;

@Mapper
public interface TestDAO {
	String today() throws SQLException;
	int    sum(HashMap<String, Integer> map) throws SQLException;
	int    mul(HashMap<String, Integer> map) throws SQLException;
	TestVO vo(TestVO testVO) throws SQLException;
	
	
	// 회원 전체 인원수
	int selectCount(HashMap<String, Object> vomap) throws SQLException;
	List<MemberVO> selectByMemberList(HashMap<String, Object> hashMap) throws SQLException;
}
