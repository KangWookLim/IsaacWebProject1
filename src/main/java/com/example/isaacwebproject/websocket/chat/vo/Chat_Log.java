package com.example.isaacwebproject.websocket.chat.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Chat_Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chat_log_id;

    @Column(length = 100)
    @NotNull
    private String mem_id;

    @CreatedDate
    private LocalDateTime createdDate;

    @Column(length = 500)
    @NotNull
    private String message;

}
