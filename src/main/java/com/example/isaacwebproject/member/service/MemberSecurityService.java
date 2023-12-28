package com.example.isaacwebproject.member.service;

import com.example.isaacwebproject.error.exception.DoNotLoginException;
import com.example.isaacwebproject.member.mapper.MemberMapper;
import com.example.isaacwebproject.member.repo.memberRepo;
import com.example.isaacwebproject.member.vo.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
    private final MemberMapper memberMapper;
    private final memberRepo memberrepo;
    @Override
    public Member loadUserByUsername(String ID) throws UsernameNotFoundException {
        Optional<Member> OpMember = Optional.empty();
        try{
            OpMember = Optional.of(memberrepo.findById(ID));
        }catch (NullPointerException e){
            throw new DoNotLoginException();
        }

        Member member = OpMember.get();

        return Member.builder()
                .ID(member.getID())
                .PW(member.getPW())
                .NICKNAME(member.getNICKNAME())
                .COIN(member.getCOIN())
                .EXP(member.getEXP())
                .CREATEDATE(member.getCREATEDATE())
                .UPDATEDATE(member.getUPDATEDATE())
                .build();

    }
}
