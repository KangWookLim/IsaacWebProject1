package com.example.isaacwebproject.inven.contorller;

import com.example.isaacwebproject.config.SessionConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class InvenContorller {
    SessionConfig sessionConfig;
    @RequestMapping("/inven")
    public ModelAndView inven(){
        ModelAndView view = new ModelAndView();
        view.setViewName("views/inven");
        return view;
    }
}
