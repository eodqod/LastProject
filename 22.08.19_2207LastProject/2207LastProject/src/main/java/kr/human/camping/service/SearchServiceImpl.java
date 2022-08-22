package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.SearchDAO;
import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchPagingVO;

@Service("serchService")
@Transactional
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchDAO searchDAO;

	@Override
	public List<CompanyVO> CompanyAreaCode(int areacode) {
		List<CompanyVO> companyVO = null;
		try {
			companyVO = searchDAO.searchByareacode(areacode);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return companyVO;
	}

	@Override
	public SearchPagingVO<CompanyVO> CompanyCode(int areacode, int detailcode,List<String> eco,List<String>roomtype,List<String>theme,int currentPage, int pageSize, int blockSize,String keyword) {
		SearchPagingVO<CompanyVO> pagingVO = null;
		HashMap<String, Object> vomap = new HashMap<>();

		
		try {
			
			vomap.put("areacode", areacode);
			vomap.put("detailcode", detailcode);
			if(keyword !=null) {
				vomap.put("keyword", keyword);
			}
			if(eco !=null) {
				vomap.put("eco", eco);
			}
			if(roomtype !=null) {
				vomap.put("roomtype", roomtype);
			}
			if(theme !=null) {
				vomap.put("theme", theme);
			}
			int totalCount = searchDAO.searchCount(vomap);
			System.out.println("serviceImpl: " + vomap);
			System.out.println("serviceImpl: " + totalCount + "개");
			
			pagingVO = new SearchPagingVO<>(totalCount, currentPage, pageSize, blockSize);
			vomap.put("startNo", pagingVO.getStartNo());
			vomap.put("endNo", pagingVO.getEndNo());
			
			System.out.println("ServiceImpl에서의 CompanyCode(pagingmap)호출 :  " + vomap);
			System.out.println("----------------------------------------------------------");
			System.out.println("ServiceImpl에서의 CompanyCode(pagingVO)호출 :  " + pagingVO);
			if(pagingVO.getTotalCount()>0)
				pagingVO.setList(searchDAO.searchBycode(vomap));
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		System.out.println("----------------------------------------------------------");
		System.out.println("ServiceImpl에서의 리턴 되기전 CompanyCode(pagingVO)호출 :  " + pagingVO);
		return pagingVO;
	}

	
	
	
}
