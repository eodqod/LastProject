package kr.human.camping.service;

import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;

public interface FileBoardService {
	// 목록보기
	PagingVO<FileBoardVO> selectList(int currentPage, int pageSize, int blockSize);
	// 저장하기
	boolean insert(FileBoardVO fileBoardVO);
	// 수정하기
	boolean update(FileBoardVO fileBoardVO);
	// 삭제하기
	boolean delete(FileBoardVO fileBoardVO);
}
