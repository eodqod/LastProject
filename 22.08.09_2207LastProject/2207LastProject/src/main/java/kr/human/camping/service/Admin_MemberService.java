package kr.human.camping.service;

import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;

public interface Admin_MemberService {
	// 전체 회원 목록보기
	PagingVO<MemberVO> selectByMemberList(int currentPage, int pageSize, int blockSize);
}
