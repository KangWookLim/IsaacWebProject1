package com.example.isaacwebproject.websocket.chat.vo;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChatVo {
    private String mem_id;
    private String time;
    private String message;
    private int id;
}
