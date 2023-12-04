package com.example.isaacwebproject.login.controller;

import com.example.isaacwebproject.login.service.LoginService;
import com.example.isaacwebproject.login.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService service;


    @RequestMapping(value = "/mem/login")
    @ResponseBody
    public Map<String, Object> loginProcess(@RequestParam(name="ID", required = true) String ID,
                                            @RequestParam(name="PW", required = true) String PW,
                                            HttpServletRequest request) {

        Map<String, Object> resultMap = new HashMap<>();

        try{
            System.out.println(ID);
            System.out.println(PW);
            Map<String, Object> param = new HashMap<>();
            param.put("ID", ID);
            param.put("PW", PW);

            LoginVO login = service.getLogin(param);
            System.out.println(login);
            if(login == null) {
                throw new Exception("로그인 실패!");

            }else {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", login);
                resultMap.put("resultCode", 200);
            }
        }catch (Exception e) {
            resultMap.put("resultCode", 500);
            e.printStackTrace();
        }
        return resultMap;
    }
}
