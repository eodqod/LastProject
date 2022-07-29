package kr.human.camping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import kr.human.camping.service.MemberService;

@Controller("MemberController")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	// 로그인
	// 회원정보가져오기
	// 로그아웃
	// 가입메일 전송하기
	// 임시비밀번호 발급
	// 
	
}
