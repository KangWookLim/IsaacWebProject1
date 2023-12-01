package com.example.isaacwebproject.member.vo;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
public class Member {

    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class Request {
        private String userID;
        private String PW;
        private String nickname;
    }
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GetUserInfo {
        private String UserImg;
        private String UserId;
    }
}
