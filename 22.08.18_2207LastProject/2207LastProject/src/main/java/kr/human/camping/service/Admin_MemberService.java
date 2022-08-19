package kr.human.camping.service;

import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;

public interface Admin_MemberService {
	// 전체 회원 목록보기
	PagingVO<MemberVO> selectByMemberList(int currentPage, int pageSize, int blockSize);
	// 휴먼 회원 가져오기
	PagingVO<MemberVO> selectByDormancyMember(String dormancy, int currentPage, int pageSize, int blockSize);
}
