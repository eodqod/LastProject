package kr.human.camping.service;

import kr.human.camping.dao.MemberDAO;
import kr.human.camping.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
    private final MemberDAO userMapper;

    @Override
    public UserVo loadUserByUsername(String id) throws UsernameNotFoundException {
        //여기서 받은 유저 패스워드와 비교하여 로그인 인증
        UserVo userVo = userMapper.getUserAccount(id);
        if (userVo == null){
            throw new UsernameNotFoundException("User not authorized.");
        }

        return userVo;
    }
}