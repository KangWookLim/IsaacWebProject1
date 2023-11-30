package com.example.isaacwebproject.member.vo;

import lombok.*;

public class Member {

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Request {
        private int memId;
        private String userID;
        private String PW;
        private String nickname;
        private int coin;
        private String createdate;
        private int exp;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetUserInfo{
        private String UserImg;
        private String UserId;
    }

}
