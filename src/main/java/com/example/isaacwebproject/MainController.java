package com.example.isaacwebproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    @GetMapping("/")
    public ModelAndView mainPage() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/home");
        return view;
    }

}

