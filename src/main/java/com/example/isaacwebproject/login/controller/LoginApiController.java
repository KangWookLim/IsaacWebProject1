package com.example.isaacwebproject.login.controller;

import com.example.isaacwebproject.config.SessionConfig;
import com.example.isaacwebproject.login.service.LoginService;
import com.example.isaacwebproject.member.vo.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginApiController {
    private final LoginService service;
    private final SessionConfig sessionConfig;

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
                if(sessionConfig.doubleLoginCheck(ID)) {
                    throw new Exception("중복 로그인 입니다.");
                }else {
                    session.setAttribute("userInfo", member.getID());
                    sessionConfig.sessionCreated(session);
                    resultMap.put("resultCode", 200);
                    session.setMaxInactiveInterval(60 * 30);
                }
            }
        }catch (Exception e) {
            resultMap.put("resultCode", 500);
            e.printStackTrace();
        }
        return resultMap;
    }

    @GetMapping("/mem/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();

        if(request.getSession().getAttribute("userInfo") != null) {
            sessionConfig.sessionDestroyed(request.getSession().getAttribute("userInfo"));
            sessionConfig.countsessions();
            request.getSession().removeAttribute("userInfo");
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        view.setViewName("redirect:/");
        return view;
    }
}
