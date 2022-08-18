package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.FileBoardDAO;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;

@Service("fileBoardService")
@Transactional
public class FileBoardServiceImpl  implements FileBoardService{

	@Autowired
	private FileBoardDAO fileBoardDAO;

	@Override
	public PagingVO<FileBoardVO> selectList(int currentPage, int pageSize, int blockSize) {
		PagingVO<FileBoardVO> pagingVO = null;
		try {
			int totalCount = fileBoardDAO.selectCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<FileBoardVO> list = fileBoardDAO.selectList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}
	
	@Override
	public FileBoardVO selectByIdx(int idx, boolean isClick) {
		FileBoardVO fileBoardVO= null;
		//------------------------------------------------------------

		try {
			//--------------------------------------------------------------------
			// 1. 해당 글번호의 글을 가져온다.
			fileBoardVO = fileBoardDAO.selectByIdx(idx);

			if(fileBoardVO!=null && isClick) { // 글이 존재하면서 isClick이 참이면 조회수 증가
				fileBoardVO.setClickCount(fileBoardVO.getClickCount()+1); // 나의 조회수 증가
//				fileBoardDAO.increment(sqlSession, idx); // DB의 조회수 증가
			}
			//--------------------------------------------------------------------
		}catch (Exception e) {
			e.printStackTrace();
		}
		//----------------------------------------------------------------------------------
		return fileBoardVO;
	}
	

	@Override
	public boolean insert(FileBoardVO fileBoarVO) {
		boolean result = false;
		if(fileBoarVO!=null) {
			try {
				fileBoardDAO.insert(fileBoarVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean update(FileBoardVO fileBoarVO) {
		boolean result = false;
		if(fileBoarVO!=null) {
			try {
				FileBoardVO dbVO = fileBoardDAO.selectByIdx(fileBoarVO.getIdx()); // DB에서 원본 가져오기
				fileBoardDAO.update(fileBoarVO);
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public boolean delete(FileBoardVO fileBoarVO) {
		boolean result = false;
		if(fileBoarVO!=null) {
			try {
				FileBoardVO dbVO = fileBoardDAO.selectByIdx(fileBoarVO.getIdx()); // DB에서 원본 가져오기
				fileBoardDAO.delete(fileBoarVO.getIdx());
				result = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


}
