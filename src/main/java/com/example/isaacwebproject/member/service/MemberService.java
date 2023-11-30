package com.example.isaacwebproject.member.service;

import com.example.isaacwebproject.member.mapper.MemberMapper;
import com.example.isaacwebproject.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public int checkUser(String userID) throws Exception{
        return memberMapper.checkUser(userID);
    }

    public int insertMember(Member.Request request) throws Exception {
        return memberMapper.insertMember(request);
    }
    
}
