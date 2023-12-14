package com.example.isaacwebproject.error.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/error")
@Controller
public class forAjaxErrorController {
    @RequestMapping("/401")
    public ModelAndView ajaxerror(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/error/error401");
        return modelAndView;
    }

    @RequestMapping("/404")
    public ModelAndView dataerror(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/error/error404");
        return modelAndView;
    }
}
