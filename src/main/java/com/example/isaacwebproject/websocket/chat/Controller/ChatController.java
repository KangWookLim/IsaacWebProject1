package com.example.isaacwebproject.websocket.chat.Controller;

import com.example.isaacwebproject.error.exception.DoNotLoginException;
import com.example.isaacwebproject.error.exception.ServerNotFoundException;
import com.example.isaacwebproject.websocket.Handler.CustomWebSocketHandler;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jdk.jfr.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
@RequiredArgsConstructor
public class ChatController {
    private final CustomWebSocketHandler customWebSocketHandler;
    @GetMapping("/chat")
    public ModelAndView chatHome(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String memId = session.getAttribute("userInfo").toString();
        if(memId == null) {
            throw new DoNotLoginException("Login please");
        }else{
            customWebSocketHandler.setHttpSession(request.getSession());
            if(customWebSocketHandler.getSocket_HttpSessionBindMap().containsKey(customWebSocketHandler.getHttpSession())){
                throw new RuntimeException("You cannot connect to the chat server multiple times.");
            }else{
                return new ModelAndView("views/chat");
            }
        }
    }
}
