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
@Table(name="user_info")
public class Users {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(length = 50, nullable = false)
    private String username;

    @CreationTimestamp
    @Column(name = "reg_dtime")
    private LocalDateTime regDTime;

    @UpdateTimestamp
    @Column(name = "upd_dtime")
    private LocalDateTime updDtime;

    @Builder
    private Users(Long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

}
