package kr.human.camping.service;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.HashMap;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import kr.human.SHA256.SHA256;
import kr.human.camping.dao.MemberDAO;
import kr.human.camping.vo.MemberVO;

@Service("MemberService")
@Transactional
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberDAO memberDAO;
	
	@Override
	public void MemberInsert(MemberVO vo) {
		SHA256 sha256 = new SHA256();
		String password = vo.getPassword();
		try {
			vo.setPassword(sha256.encrypt(password));
			memberDAO.insert(vo);
			memberDAO.insertAccess(vo);
		} catch(SQLException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void MemberUpdate(MemberVO vo) {
		try {
			memberDAO.update(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public MemberVO MeberInfo(String id) {
		MemberVO vo = new MemberVO();
		try {
			vo = memberDAO.selectByMemberInfo(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public void MemberDelete(MemberVO vo) {
		try {
			memberDAO.delete(vo);
			memberDAO.deleteAccess(vo.getId());
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean Login(String id, String password) {
		SHA256 sha256 = new SHA256();
		boolean result = true;
		HashMap<String, String> map = new HashMap<>();
		MemberVO vo = new MemberVO();
		try {
			map.put("id", id);
			map.put("password", sha256.encrypt(password));
			if(memberDAO.IDOverlap(id) != 0) {
				if(memberDAO.login(map) != 0) {
					vo = memberDAO.selectByMemberInfo(id);
					
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
	}


	@Override
	public int IDOverlap(String id) {
		int result = 0;
		try {
			result = memberDAO.IDOverlap(id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int EmailOverlap(String email) {
		int result = 0;
		try {
			result = memberDAO.EmailOverlap(email);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void changePassword(HashMap<String, String> map) {
		SHA256 sha256 = new SHA256();
		String result = map.get("password");
		
		try {
			String password = sha256.encrypt(result);
			map.put("password",password);
			memberDAO.changePassword(map);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String findID(String email) {
		String id = null;
		
		try {
			id = memberDAO.findID(email);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	@Override
	public boolean passwordcheck(String password, MemberVO vo) {
		boolean Ischeck = true;
		SHA256 sha256 = new SHA256();
		try {
			if(!vo.getPassword().equals(sha256.encrypt(password))) {
				Ischeck = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return Ischeck;
	}

}
