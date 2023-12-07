package com.example.isaacwebproject.websocket.chat.vo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OutputMessage {
    private String nickname;
    private String message;
    private String time;

    public OutputMessage(Object nickName, Object text, String time) {
        this.nickname = nickName.toString();
        this.message = text.toString();
        this.time = time;
    }
}
