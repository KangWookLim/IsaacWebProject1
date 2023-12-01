package com.example.isaacwebproject.member.mapper;

import com.example.isaacwebproject.member.vo.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    int checkUser(@Param("ID") String ID);

    int insertMember(Member.Request request) throws Exception;

    Member.GetUserInfo getMemberInfo(@Param("ID") String boardId) throws Exception;
}
