package com.example.isaacwebproject.websocket.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ChatController {

   @GetMapping("/chat")
   public ModelAndView chatHome() {
      ModelAndView chat = new ModelAndView("views/chat");
      return chat;
   }

}
