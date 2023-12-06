package com.example.isaacwebproject.inven.contorller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class InvenContorller {

    @RequestMapping("/inven")
    public ModelAndView inven(){
        ModelAndView view = new ModelAndView();
        view.setViewName("views/inven");
        return view;
    }
}
