package com.example.isaacwebproject.websocket.chat.Repo;

import com.example.isaacwebproject.websocket.chat.vo.Chat_Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatJPARepo extends JpaRepository<Chat_Log, Long> {

}
