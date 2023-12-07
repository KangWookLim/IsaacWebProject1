package com.example.isaacwebproject.websocket.socketServer;

import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.*;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.*;

@RequestMapping("/chat")
@ServerEndpoint("/chat")
public class MyWebSocketServer {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("onOpen: " + session);
        sessions.add(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        for (Session s : sessions) {
            if(s.isOpen()){
                try {
                    s.getBasicRemote().sendText(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @OnClose
    public void onClose(Session session) {
        sessions.remove(session);
    }
}
