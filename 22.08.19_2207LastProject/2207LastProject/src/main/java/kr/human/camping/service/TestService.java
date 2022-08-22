package kr.human.camping.service;

import java.util.List;

import kr.human.camping.vo.CompanyVO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchPagingVO;
import kr.human.camping.vo.SelectRolePagingVO;
import kr.human.camping.vo.TestVO;

public interface TestService {
	String today();
	int    sum(int num1, int num2);
	int    mul(int num1, int num2);
	TestVO vo(int num1, int num2);
	SelectRolePagingVO<MemberVO> selectByMemberList(String role, int p, int s, int b);
}

