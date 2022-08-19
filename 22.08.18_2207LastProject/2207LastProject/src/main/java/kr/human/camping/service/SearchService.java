package kr.human.camping.service;

import java.util.List;

import kr.human.camping.vo.CompanyVO;

public interface SearchService {
	//1.지역코드만 검색한 경우
	List<CompanyVO> CompanyAreaCode(int areacode);
	
	//2.지역코드, 상세코드 검색한 경우
	List<CompanyVO> CompanyCode(int areacode, int detailcode, List<String> eco,List<String>roomtype,List<String> theme);
	
	//3.업체 총 갯수
	public int selectCompanyCount();
	
	//2.지역코드, 상세코드 검색한 경우
	//List<CompanyVO> CompanyCode(int areacode, int detailcode,String eco,String roomtype,String theme);
}
