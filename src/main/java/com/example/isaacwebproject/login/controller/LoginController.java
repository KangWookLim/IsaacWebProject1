package com.example.isaacwebproject.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {




    @GetMapping("/mem/login")
    public ModelAndView loginForm() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/login");

        return view;
    }





    @GetMapping("/mem/error")
    public ModelAndView PageError( ) {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/member/error");
        return view;
    }


    @GetMapping("/mem/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();

        if(request.getSession().getAttribute("userInfo") != null) {
            request.getSession().removeAttribute("userInfo");
            request.getSession().invalidate();
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        view.setViewName("redirect:/");
        return view;
    }
}
