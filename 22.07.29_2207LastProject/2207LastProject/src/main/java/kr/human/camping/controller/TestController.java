package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.human.camping.service.CSVService;
import kr.human.camping.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;

	
	@RequestMapping(value = "/today")
	public String today(Model model) {
		model.addAttribute("today", testService.today());
		return "today";
	}
	
	@RequestMapping(value = "/")
	public String home(Model model) {
		model.addAttribute("serverTime", testService.today());
		return "index";
	}
	
	
}
