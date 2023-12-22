package com.example.isaacwebproject.gameServer.mem.Vo;

import lombok.*;


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
    private String createdate;
    private String updatedate;
}
