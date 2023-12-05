package com.example.isaacwebproject.member.vo;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails {
    private int MEMID;
    private String ID;
    private String PW;
    private String NICKNAME;
    @Builder.Default
    private int COIN = 0;
    @Builder.Default
    private int EXP = 0;
    private String CREATEDATE;
    private String UPDATEDATE;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return PW;
    }
    @Override
    public String getUsername() {
        return NICKNAME;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
