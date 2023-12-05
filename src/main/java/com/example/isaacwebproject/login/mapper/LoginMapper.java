package com.example.isaacwebproject.login.mapper;

import com.example.isaacwebproject.member.vo.Member;
import org.apache.ibatis.annotations.
        Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {
    Member getLogin(Map<String, Object> param) throws Exception;

}
