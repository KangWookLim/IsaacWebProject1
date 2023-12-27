package com.example.isaacwebproject.member.mapper;

import com.example.isaacwebproject.member.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    int checkId(@Param("ID") String ID);
    int checkNickname(@Param("NICKNAME") String NICKNAME);

    Boolean insertMember(Member member);

    Member getMemberById(@Param("ID") String ID);

    boolean updateCoinById(@Param("ID") String ID, @Param("COIN") int COIN);

    boolean updateEXPById(@Param("ID") String ID, @Param("EXP") int EXP);
}
