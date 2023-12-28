package com.example.isaacwebproject.gameServer.mem.Vo;

import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class memVo {
    private int memid;
    private String id;
    private String pw;
    private String nickname;
    private int coin;
    private int exp;
    private LocalDateTime createdate;
    private LocalDateTime updatedate;
}
