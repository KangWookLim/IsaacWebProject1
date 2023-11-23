package com.example.isaacwebproject.member.vo;

import lombok.*;

public class Member {

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Request{
        private String memId;
        private String memName;
        private String memPasswd;
        private int memBirth;
        private String memGender;
        private String mailId;
        private String mailAddr;
        private String memAuth;


        public String getMemEmail() {
            return this.mailId+"@"+this.mailAddr;
        }
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
