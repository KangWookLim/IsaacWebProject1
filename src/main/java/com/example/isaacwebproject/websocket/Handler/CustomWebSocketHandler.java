package com.example.isaacwebproject.websocket.Handler;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CustomWebSocketHandler implements WebSocketHandler {
    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>() {
    };
    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception {
//        httpSession.getAttribute("userInfo");
        System.out.println("웹소켓 연결이 열렸습니다: " + session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println("message: " + message);
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
