package com.example.isaacwebproject.error.Controller;

import com.example.isaacwebproject.error.exception.DataNotFoundException;
import com.example.isaacwebproject.error.exception.DoNotLoginException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@ControllerAdvice
public class MyErrorController {
    @ExceptionHandler(DoNotLoginException.class)
    public ModelAndView error401(DoNotLoginException e){
        System.out.println("실행중");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("views/error/error401");
        modelAndView.addObject("message", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ModelAndView error404(DataNotFoundException e ,HttpServletResponse response){
        return new ModelAndView("views/error/error404");
    }
}