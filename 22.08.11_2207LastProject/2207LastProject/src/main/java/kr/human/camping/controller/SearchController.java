package kr.human.camping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
			 Model model) {
		
		if(area2 == null) {
			List<CompanyVO> list = new ArrayList<CompanyVO>(); 
			list = searchService.CompanyAreaCode(area1);
			model.addAttribute("list", list);		
		}else {		
			List<CompanyVO> list = new ArrayList<CompanyVO>();
			System.out.println("확인!!!!!!" + area1 + "+" + area2);
			list =searchService.CompanyCode(area1, area2);
			System.out.println(list);
			model.addAttribute("list",list);
		}
		model.addAttribute("area1",area1);
		model.addAttribute("area2",area2);
		return "search";
	}
	
}
