package com.example.isaacwebproject.websocket.Handler;

import com.example.isaacwebproject.error.exception.DoNotLoginException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Getter
@Setter
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler implements WebSocketHandler {

    private final Map<HttpSession,WebSocketSession> Socket_HttpSessionBindMap = new ConcurrentHashMap<>();
    private HttpSession httpSession;
    private final ObjectMapper mapper;
    private static final Map<String, WebSocketSession> chatsessions = new ConcurrentHashMap<>();

    private Map<Long,Map<String,WebSocketSession>> chatRoomSessions = new ConcurrentHashMap<>();


    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session){
        if(Socket_HttpSessionBindMap.containsKey(httpSession)){
            System.out.println("실행");
            throw new RuntimeException("You cannot connect to the chat server multiple times.");
        }else{
            Socket_HttpSessionBindMap.put(httpSession,session);
            System.out.println(Socket_HttpSessionBindMap);
        }
    }

    @Override
    public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) throws Exception {
        sendMessage(session,message);
        System.out.println("message: " + message.getPayload().toString());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        System.out.println("서버통신 오류 " + session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        Socket_HttpSessionBindMap.remove(httpSession);
        System.out.println("연결이 종료 되었습니다.");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        Socket_HttpSessionBindMap.values().forEach(s -> {
            try {
                s.sendMessage(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
