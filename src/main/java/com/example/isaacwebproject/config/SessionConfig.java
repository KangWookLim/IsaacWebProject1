package com.example.isaacwebproject.config;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
@Configuration
public class SessionConfig implements HttpSessionListener {
    private static final Map<Object, HttpSession> sessions = new ConcurrentHashMap<>();
    public synchronized static boolean doubleLoginCheck(String compareId){
        return sessions.containsKey(compareId);
    }

    public void sessionCreated(HttpSession se) {
        sessions.put(se.getAttribute("userInfo"), se);
        System.out.println(sessions);
    }

    public void countsessions(){
        System.out.println(sessions.size());
    }

    public void sessionDestroyed(Object ID) {
        if(sessions.containsKey(ID)) {
            sessions.get(ID).invalidate();
            sessions.remove(ID);
        }
    }
}
