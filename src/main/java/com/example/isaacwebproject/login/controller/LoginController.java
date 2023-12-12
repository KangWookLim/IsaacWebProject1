package com.example.isaacwebproject.login.controller;

import com.example.isaacwebproject.config.SessionConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final SessionConfig sessionConfig;



    @GetMapping("/mem/login")
    public ModelAndView loginForm() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/login");

        return view;
    }
}
