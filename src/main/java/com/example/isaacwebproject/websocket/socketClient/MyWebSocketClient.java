package com.example.isaacwebproject.websocket.socketClient;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.context.annotation.Bean;

import java.net.URI;
import java.util.concurrent.CountDownLatch;

public class MyWebSocketClient extends WebSocketClient {
    public CountDownLatch countThread;
    public MyWebSocketClient(URI serverUri) {
        super(serverUri);
        this.countThread = new CountDownLatch(1);
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("서버에 연결되었습니다.");
        countThread.countDown();
    }

    @Override
    public void onMessage(String message) {
        System.out.println("서버 메세지 : " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("서버 종료");
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }
}
