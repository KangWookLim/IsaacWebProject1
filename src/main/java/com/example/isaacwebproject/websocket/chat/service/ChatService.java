package com.example.isaacwebproject.websocket.chat.service;

import com.example.isaacwebproject.websocket.chat.Repo.ChatRepo;
import com.example.isaacwebproject.websocket.chat.vo.ChatVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepo chatRepo;

    public List<ChatVo>  findAllChat(){
        System.out.println(chatRepo.findAllChat());
        return chatRepo.findAllChat();
    }
    public void addChat(String mem_id, String message){
        chatRepo.addChatLog(mem_id, message);
    }
}
