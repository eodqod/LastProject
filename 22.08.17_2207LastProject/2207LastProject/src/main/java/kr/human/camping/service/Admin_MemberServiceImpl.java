package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.human.camping.dao.Admin_MemberDAO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;

@Service("Admin_MemberService")
@Transactional
public class Admin_MemberServiceImpl implements Admin_MemberService{

	@Autowired
	private Admin_MemberDAO memberDAO;

	@Override
	public PagingVO<MemberVO> selectByMemberList(int currentPage, int pageSize, int blockSize) {
		PagingVO<MemberVO> pagingVO = null;
		try {
			int totalCount = memberDAO.selectCount();
			pagingVO = new PagingVO<>(totalCount, currentPage, pageSize, blockSize);
			HashMap<String, Integer> hashMap = new HashMap<>();
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<MemberVO> list = memberDAO.selectByMemberList(hashMap);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}

}
