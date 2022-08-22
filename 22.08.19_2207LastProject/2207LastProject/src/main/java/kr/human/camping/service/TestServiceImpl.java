package kr.human.camping.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.human.camping.dao.TestDAO;
import kr.human.camping.vo.MemberVO;
import kr.human.camping.vo.PagingVO;
import kr.human.camping.vo.SearchPagingVO;
import kr.human.camping.vo.SelectRolePagingVO;
import kr.human.camping.vo.TestVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("testService")
public class TestServiceImpl implements TestService{

	@Autowired
	private TestDAO testDAO;

	@Override
	public String today() {
		String today = "";
		try {
			today = testDAO.today();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return today;
	}

	@Override
	public int sum(int num1, int num2) {
		int sum = 0;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("num1", num1);
		map.put("num2", num2);
		try {
			sum = testDAO.sum(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;
	}

	@Override
	public int mul(int num1, int num2) {
		int sum = 0;
		HashMap<String, Integer> map = new HashMap<>();
		map.put("num1", num1);
		map.put("num2", num2);
		try {
			sum = testDAO.mul(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sum;	}

	@Override
	public TestVO vo(int num1, int num2) {
		TestVO testVO = new TestVO();
		testVO.setNum1(num1);
		testVO.setNum2(num2);
		try {
			testVO = testDAO.vo(testVO);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testVO;	
	}

	@Override
	public SelectRolePagingVO<MemberVO> selectByMemberList(String role, int p, int s, int b) {
		SelectRolePagingVO<MemberVO> pagingVO = null;
		HashMap<String, Object> vomap = new HashMap<>();
		try {
			vomap.put("role", role);
			int totalCount = testDAO.selectCount(vomap);
			log.info("testservice vomap 호출 : " + vomap);
			log.info("testservice totalCount 호출 : " + totalCount);
			pagingVO = new SelectRolePagingVO<>(totalCount, p, s, b, role);
			HashMap<String, Object> hashMap = new HashMap<>();
			hashMap.put("role", role);
			hashMap.put("startNo", pagingVO.getStartNo());
			hashMap.put("endNo", pagingVO.getEndNo());
			List<MemberVO> list = testDAO.selectByMemberList(hashMap);
			log.info("testservice hashMap 호출 : " + hashMap);
			log.info("testservice list 호출 : " + list);
			pagingVO.setList(list);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pagingVO;
	}
	
}
