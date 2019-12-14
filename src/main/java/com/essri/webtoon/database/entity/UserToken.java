package com.essri.webtoon.database.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_token")
public class UserToken {
    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "token", length = 100)
    private String token;

    @Column(name = "refresh_token")
    private String refreshToken;

    @CreationTimestamp
    @Column(name = "reg_dtime")
    private LocalDateTime regDTime;

    @UpdateTimestamp
    @Column(name = "upd_dtime")
    private LocalDateTime updDtime;

    @Builder
    public UserToken(Long userId, String token, String refreshToken) {
        this.userId = userId;
        this.token = token;
        this.refreshToken = refreshToken;
    }
}