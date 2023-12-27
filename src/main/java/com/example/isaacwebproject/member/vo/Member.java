package com.example.isaacwebproject.member.vo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MEMID;

    @Column(length = 100, unique = true)
    @NotNull
    private String ID;

    @Column(length = 100)
    @NotNull
    private String PW;

    @Column(length = 100)
    @NotNull
    private String NICKNAME;

    @ColumnDefault("0")
    private int COIN;

    @ColumnDefault("0")
    private int EXP = 0;

    @CreatedDate
    private LocalDateTime CREATEDATE;

    @LastModifiedDate
    private LocalDateTime UPDATEDATE;

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
