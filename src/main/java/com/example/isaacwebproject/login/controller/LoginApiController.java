package com.example.isaacwebproject.login.controller;

import com.example.isaacwebproject.login.service.LoginService;
import com.example.isaacwebproject.member.vo.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApiController {

    private final LoginService service;


    @RequestMapping("/mem/login")
    @ResponseBody
    public Map<String, Object> loginProcess(@RequestParam(name="ID") String ID,
                                            @RequestParam(name="PW") String PW,
                                            HttpServletRequest request) throws Exception {

        Map<String, Object> resultMap = new HashMap<>();

        try{
            System.out.println(ID);
            System.out.println(PW);
            Member member = service.getMemberById(ID);
            if(!service.PWCheck(member, PW)) {
                throw new Exception("로그인 실패!");

            }else {
                HttpSession session = request.getSession();
                session.setAttribute("userInfo", member);
                resultMap.put("resultCode", 200);
                session.setMaxInactiveInterval(60 * 30);
            }
        }catch (Exception e) {
            resultMap.put("resultCode", 500);
            e.printStackTrace();
        }
        return resultMap;
    }
}
