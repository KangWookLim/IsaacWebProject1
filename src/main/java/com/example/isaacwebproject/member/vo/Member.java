package com.example.isaacwebproject.member.vo;

import lombok.*;

import java.util.Collection;

@Getter
public class Member {

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    @Setter
    public static class Request {
        private String ID;
        private String PW;
        private String nickname;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetUserInfo {
        private String UserImg;
        private String Id;
    }
}
