package com.example.isaacwebproject.login.service;

import com.example.isaacwebproject.login.mapper.LoginMapper;
import com.example.isaacwebproject.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginService {


    private final LoginMapper mapper;

    public LoginVO getLogin(Map<String, Object>param) throws Exception {
        return mapper.getLogin(param);
    }

}
