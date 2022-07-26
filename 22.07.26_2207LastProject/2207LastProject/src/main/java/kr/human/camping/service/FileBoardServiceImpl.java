package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

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
			hashMap.put("endNo", pagingVO.getPageSize());
			List<FileBoardVO> list = fileBoardDAO.selectList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
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
