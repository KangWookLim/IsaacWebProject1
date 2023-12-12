package com.example.isaacwebproject.websocket.chat.controller;

import com.example.isaacwebproject.websocket.chat.vo.OutputMessage;
import com.example.isaacwebproject.websocket.socketServer.MyWebSocketServer;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequiredArgsConstructor
public class ChatController {

   @GetMapping("/chat")
   public ModelAndView chatHome(){
      ModelAndView chat = new ModelAndView("views/board/chat");
      return chat;
   }

}
