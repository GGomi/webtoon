package com.essri.webtoon.database.entity;

import com.essri.webtoon.user.model.UserType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "user_info")
    private List<UserAuthority> authorities;

    @CreationTimestamp
    @Column(name = "reg_dtime")
    private LocalDateTime regDTime;

    @UpdateTimestamp
    @Column(name = "upd_dtime")
    private LocalDateTime updDtime;

    @Builder
    private Users(Long userId, String username, UserType userType, List<UserAuthority> authorities, String email) {
        this.userId = userId;
        this.username = username;
        this.userType = userType;
        this.authorities = authorities;
        this.email = email;
    }

}
