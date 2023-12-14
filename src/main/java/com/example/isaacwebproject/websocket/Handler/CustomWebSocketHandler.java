package com.example.isaacwebproject.websocket.Handler;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class CustomWebSocketHandler implements WebSocketHandler {
    private static final Map<String, WebSocketSession> sessionMap = new ConcurrentHashMap<>();
    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) throws Exception {
//        httpSession.getAttribute("userInfo");
        System.out.println("웹소켓 연결이 열렸습니다: " + session);
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
        System.out.println("연결이 종료 되었습니다.");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public void sendMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        session.sendMessage(message);
    }
}
