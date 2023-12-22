package com.example.isaacwebproject;
import com.example.isaacwebproject.gameServer.miniprojectServer.ServerControl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class MainController {
    @RequestMapping("/")
    public String root(){
        return "redirect:/home";
    }
    @RequestMapping("/home")
    public ModelAndView home(){
        return new ModelAndView("views/home");
    }
}

