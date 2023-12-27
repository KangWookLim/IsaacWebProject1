package com.example.isaacwebproject.websocket.chat.service;

import com.example.isaacwebproject.websocket.chat.Repo.ChatJPARepo;
import com.example.isaacwebproject.websocket.chat.Repo.ChatRepo;
import com.example.isaacwebproject.websocket.chat.vo.Chat_Log;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {
    private final ChatRepo chatRepo;
    private final ChatJPARepo chatJPARepo;

    public List<Chat_Log>  findAllChat(){
        System.out.println(chatRepo.findAllChat());
        return chatRepo.findAllChat();
    }
    public void addChat(String mem_id, String message){
        chatRepo.addChatLog(mem_id, message);
    }

    public void addChatatJAP(String mem_id,String message){
        Chat_Log chat = new Chat_Log();
        chat.setMem_id(mem_id);
        chat.setMessage(message);
        this.chatJPARepo.save(chat);
    }
}
