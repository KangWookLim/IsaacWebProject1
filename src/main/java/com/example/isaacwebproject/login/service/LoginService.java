package com.example.isaacwebproject.login.service;

import com.example.isaacwebproject.login.mapper.LoginMapper;
import com.example.isaacwebproject.member.service.MemberSecurityService;
import com.example.isaacwebproject.member.vo.Member;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class LoginService {
    private final MemberSecurityService memberSecurityService;
    private final LoginMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public Member getMemberById(String ID) {
        return memberSecurityService.loadUserByUsername(ID);
    }
    public Member getLogin(Map<String, Object>param) throws Exception {
        return mapper.getLogin(param);
    }
    public boolean PWCheck(Member member,String PW) throws Exception {
        return passwordEncoder.matches(PW, member.getPW());
    }


}
