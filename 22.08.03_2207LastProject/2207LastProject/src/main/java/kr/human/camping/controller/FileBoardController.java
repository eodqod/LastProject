package kr.human.camping.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.human.camping.service.FileBoardService;
import kr.human.camping.service.FileBoardServiceImpl;
import kr.human.camping.vo.CommVO;
import kr.human.camping.vo.FileBoardVO;
import kr.human.camping.vo.PagingVO;

@Controller
public class FileBoardController {

	@Autowired
	private FileBoardService fileBoardService;
	
	// 목록보기
	@RequestMapping("/list")
	public String selectList(
			@RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "3") int s,
			@RequestParam(required = false, defaultValue = "10") int b,
			Model model 
			){
		PagingVO<FileBoardVO> pagingVO = fileBoardService.selectList(p, s, b);
		model.addAttribute("pv", pagingVO);
		model.addAttribute("p", p);
		model.addAttribute("s", s);
		model.addAttribute("b", b);
		model.addAttribute("br", "<br>");
		model.addAttribute("newLine", "\n");
		return "list";
	}
	
	// 내용보기
	@RequestMapping("/view")
	public String selectBiIdx(@RequestParam("idx") int idx, Model model) {
		FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
		model.addAttribute("vo", vo);
		return "Notice/view";
	}

	// 삭제하기
	@RequestMapping("/delete")
	public String delete(@RequestParam("idx") int idx, Model model) {
		FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
		model.addAttribute("vo", vo);
		return "Notice/delete";
	}

	// 새글쓰기
	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
	@ResponseBody
	public String insert(@ModelAttribute CommVO commVO, @ModelAttribute FileBoardVO fileBoardVO) {
		boolean result = false;
		switch (commVO.getMode()) {
		case "insert":
			result = fileBoardService.insert(fileBoardVO);
			break;
		}
		return result ? "Notice/insert":"실패";
	}

//	// 업데이트
//	@RequestMapping("/update")
//	public String selectBiIdx(@RequestParam("idx") int idx, Model model) {
//		FileBoardVO vo = fileBoardService.selectByIdx(idx, false);
//		model.addAttribute("vo", vo);
//		return "Notice/view";
//	}
	
	// 저장/수정/삭제
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String updateGet() {
		return "redirect:/list";
	}

//	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = "text/plain; charset=utf-8")
//	@ResponseBody
//	public String updatePost(@ModelAttribute CommVO commVO, @ModelAttribute FileBoardVO fileBoardVO) {
//		boolean result = false;
//		switch (commVO.getMode()) {
//		case "insert":
//			result = fileBoardService.insert(fileBoardVO);
//			break;
//		case "update":
//			result = fileBoardService.update(fileBoardVO);
//			break;
//		case "delete":
//			result = fileBoardService.delete(fileBoardVO);
//			break;
//		}
//		return result ? "성공":"실패";
//	}
}
