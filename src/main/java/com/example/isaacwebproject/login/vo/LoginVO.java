package com.example.isaacwebproject.login.vo;

import lombok.Data;

@Data
public class LoginVO {
    private final int MEMID;
    private final String ID;
    private final String PW;
    private final String NICKNAME;
    private final int COIN;
    private final int CREATEDATE;
    private final int EXP;
}
