package com.example.isaacwebproject.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ChatController {
    @GetMapping("/chat/chatrooms")
    public ModelAndView join() {
        ModelAndView view = new ModelAndView();
        view.setViewName("views/board/chat");
        return view;
    }
}
