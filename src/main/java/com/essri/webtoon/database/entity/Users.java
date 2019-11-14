package com.essri.webtoon.database.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="user_info")
@Getter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(length = 11, nullable = false)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Long userId;

    @Column(length = 50, nullable = false)
    private String username;

//    @Embedded
//    private Email email;

//    @Embedded
//    private Password password;

    @Builder
    private Users(Long userId, String username, String email) {
        this.userId = userId;
        this.username = username;
//        this.email = Email.builder().value(email).build();
    }

}
