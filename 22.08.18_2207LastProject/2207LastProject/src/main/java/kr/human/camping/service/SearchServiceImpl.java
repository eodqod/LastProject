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

	@SuppressWarnings("null")
	@Override
	public List<CompanyVO> CompanyCode(int areacode, int detailcode,List<String> eco,List<String>roomtype,List<String>theme) {
		List<CompanyVO> companyVO = null;
		HashMap<String, Object> map = new HashMap<>();
		List<String> eco_list=null;
		try {
			
			map.put("areacode", areacode);
			map.put("detailcode", detailcode);
			if(eco !=null) {
				map.put("eco", eco);
			}
			if(roomtype !=null) {
				map.put("roomtype", roomtype);
			}
			if(theme !=null) {
				map.put("theme", theme);
			}
			
			System.out.println("serviceImpl: " + map);
			companyVO = searchDAO.searchBycode(map);
			
			//System.out.println("serviceImpl: " + companyVO);
			
		}catch (Exception e){
			e.printStackTrace();
		}
		return companyVO;
	}
	/*
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
	*/

	@Override
	public int selectCompanyCount() {
		
		return 0;
	}
	
}
