package com.example.isaacwebproject.websocket.Handler;

import com.example.isaacwebproject.websocket.chat.service.ChatService;
import com.example.isaacwebproject.websocket.chat.vo.Chat_Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Setter(onMethod_={@Synchronized}) //HttpSession을 가져올때 중복해서 겹치는 오류를 방지하기 위해 한 처리
@Component
@RequiredArgsConstructor
public class CustomWebSocketHandler implements WebSocketHandler {

    private Map<HttpSession,WebSocketSession> Socket_HttpSessionBindMap = new ConcurrentHashMap<>();
    private Set<Object> connectedUsers = new HashSet<>();
    private HttpSession httpSession;
    private final ChatService chatService;
    private final ObjectMapper mapper;



    @Override
    public synchronized void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Socket_HttpSessionBindMap.put(httpSession,session);
        session.getAttributes().put("http_session",httpSession);
        session.getAttributes().put("userInfo",httpSession.getAttribute("userInfo"));
        httpSession.setAttribute("socketsession",session);
        connectedUsers.add(httpSession.getAttribute("userInfo"));
        serverMessage(httpSession.getAttribute("userInfo")+"님이 연결 되었습니다.");
    }

    @Override
    public void handleMessage(WebSocketSession session,WebSocketMessage<?> message) throws Exception {
        BroadCastMessage(session ,message);
        chatService.addChatatJAP((String)session.getAttributes().get("userInfo"),(String)message.getPayload());
        System.out.println(((HttpSession)session.getAttributes().get("http_session")).getAttribute("userInfo"));
    }


    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        System.out.println("서버통신 오류 " + exception);
    }

    @Override
    public synchronized void afterConnectionClosed(WebSocketSession session,CloseStatus closeStatus) throws Exception {
        connectedUsers.remove(session.getAttributes().get("userInfo"));
        Socket_HttpSessionBindMap.keySet().forEach(s->{
            if(s.getAttribute("socketsession").equals(session)){
                String Mem = (String)s.getAttribute("userInfo");
                Socket_HttpSessionBindMap.remove(s);
                try {
                    serverMessage(Mem + "님이 연결을 종료했습니다");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        System.out.println("연결이 종료 되었습니다.");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    public synchronized void BroadCastMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        Socket_HttpSessionBindMap.keySet().forEach(s -> {
            if(Socket_HttpSessionBindMap.get(s).equals(session)){
                try {
                    session.sendMessage(new TextMessage("나 : "+ message.getPayload()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else {
                try {
                    Socket_HttpSessionBindMap.get(s).sendMessage(new TextMessage(session.getAttributes().get("userInfo") +" : "+message.getPayload()));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public synchronized void serverMessage(String message) throws Exception {
        Socket_HttpSessionBindMap.values().forEach(s -> {
            try {
                s.sendMessage(new TextMessage("Server : "+ message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println(Socket_HttpSessionBindMap);
    }
}
