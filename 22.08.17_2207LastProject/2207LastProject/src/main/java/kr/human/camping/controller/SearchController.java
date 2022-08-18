package kr.human.camping.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.human.camping.service.SearchService;
import kr.human.camping.vo.CompanyVO;

@Controller
public class SearchController {
	

	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/search" , method = RequestMethod.GET)
	public String search(
			@RequestParam(required = false, defaultValue = "1") Integer area1,
			@RequestParam(required = false, defaultValue = "101") Integer area2,
			@RequestParam(required = false) List<String> eco,
			@RequestParam(required = false) List<String> roomtype,
			@RequestParam(required = false) List<String> theme,
			Model model) {
		
		if(area2 == null) {
			List<CompanyVO> list = new ArrayList<CompanyVO>(); 
			list = searchService.CompanyAreaCode(area1);
			model.addAttribute("list", list);		
		}else {		
			List<CompanyVO> list = new ArrayList<CompanyVO>();
			System.out.println("데이터 확인!!" + "area1 : " + area1 + "+" +"area2 : "+ area2 + "+" +"eco : "+ eco + "+" +"roomtype : "+roomtype+ "+"+"theme : " +theme);
			list =searchService.CompanyCode(area1, area2,eco,roomtype,theme);
			
			
			System.out.println("업체 목록:" + list);
			model.addAttribute("list",list);
		}
		model.addAttribute("area1",area1);
		model.addAttribute("area2",area2);
		model.addAttribute("eco",eco_list());
		model.addAttribute("roomtype",roomtype_list());
		model.addAttribute("theme",theme_list());
		return "search";
	}
	@ModelAttribute("eco")
	public Map<String, String> eco_list(){
		Map<String,String> eco= new LinkedHashMap<String, String>();
		eco.put("mountain", "산");
		eco.put("sea", "바다");
		eco.put("river", "강");
		eco.put("lake", "호수");
		return eco;
	}
	@ModelAttribute("roomtype")
	public Map<String, String> roomtype_list(){
		Map<String,String> roomtype= new LinkedHashMap<String, String>();
		roomtype.put("camping", "캠핑");
		roomtype.put("caravan", "카라반");
		roomtype.put("glamping", "글램핑");
		roomtype.put("easycamping", "이지캠핑");
		return roomtype;
	}
	@ModelAttribute("theme")
	public Map<String, String> theme_list(){
		Map<String,String> theme= new LinkedHashMap<String, String>();
		theme.put("family", "가족");
		theme.put("couple", "연인");
		theme.put("kids", "키즈");
		theme.put("pet", "반려동물");
		return theme;
	}
}
