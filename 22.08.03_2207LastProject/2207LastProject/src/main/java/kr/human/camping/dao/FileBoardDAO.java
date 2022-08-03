package kr.human.camping.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.human.camping.vo.FileBoardVO;

@Mapper
public interface FileBoardDAO {
	int selectCount() throws SQLException;
	FileBoardVO selectByIdx(int idx) throws SQLException;
	List<FileBoardVO> selectList(HashMap<String, Integer> map) throws SQLException;
	void insert(FileBoardVO fileBoardVO) throws SQLException;
	void update(FileBoardVO fileBoardVO) throws SQLException;
	void delete(int idx) throws SQLException;
	void clickCount(int idx) throws SQLException;
}
