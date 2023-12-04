package com.example.isaacwebproject.login.mapper;

import com.example.isaacwebproject.login.vo.LoginVO;
import org.apache.ibatis.annotations.
        Mapper;

import java.util.Map;

@Mapper
public interface LoginMapper {

    LoginVO getLogin(Map<String, Object> param) throws Exception;

}
