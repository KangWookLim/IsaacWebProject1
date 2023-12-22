package com.example.isaacwebproject.error.Controller;

import com.example.isaacwebproject.error.exception.AlreadyUseSockectException;
import com.example.isaacwebproject.error.exception.DoNotLoginException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@ControllerAdvice
public class MyErrorController {
    @ExceptionHandler(DoNotLoginException.class)
    public ModelAndView error401(DoNotLoginException e){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/error/error401");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
//    @ExceptionHandler(DataNotFoundException.class)
//    public String error404(Model model , DataNotFoundException e , HttpServletResponse response){
//        model.addAttribute("message",e.getMessage());
//        return "redirect:/";
//    }
    @ExceptionHandler(AlreadyUseSockectException.class)
    public ModelAndView errorSocket(AlreadyUseSockectException e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }
}
