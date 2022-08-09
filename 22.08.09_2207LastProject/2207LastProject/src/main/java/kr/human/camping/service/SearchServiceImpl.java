package kr.human.camping.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.SearchDAO;
import kr.human.camping.vo.CompanyVO;

@Service("serchService")
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
	public List<CompanyVO> CompanyCode(int areacode, int detailcode) {
		List<CompanyVO> companyVO = null;
		HashMap<String, Integer> map = new HashMap<>();
		try {
			
			map.put("areacode", areacode);
			map.put("detailcode", detailcode);
			companyVO = searchDAO.searchBycode(map);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return companyVO;
	}
	
}
