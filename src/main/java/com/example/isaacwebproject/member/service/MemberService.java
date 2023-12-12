package com.example.isaacwebproject.member.service;

import com.example.isaacwebproject.member.mapper.MemberMapper;
import com.example.isaacwebproject.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService{

    private final MemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    public int checkUser(String ID){
        return memberMapper.checkId(ID);
    }

    public Boolean insertMember(String ID,String PW, String NICKNAME) throws Exception {
        Member member = new Member();
        member.setID(ID);
        member.setPW(passwordEncoder.encode(PW));
        member.setNICKNAME(NICKNAME);
        return memberMapper.insertMember(member);
    }
    public boolean updateCoinById(String ID, int COIN){
        return memberMapper.updateCoinById(ID, COIN);
    }

    public boolean updateEXPById(String ID, int EXP){
        return memberMapper.updateEXPById(ID, EXP);
    }
    
}
