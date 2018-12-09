package com.essri.webtoon.web.data;

import com.essri.webtoon.model.Email;
import com.essri.webtoon.model.Password;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="USER_INFO")
@Getter
@Entity
public class Users {
    @Id
    @Column(length = 50, nullable = false)
    private String userId;

    @Column(length = 50, nullable = false)
    private String nickname;

    @Embedded
    private Email email;

//  Security
//    @Embedded
//    private Password passwd;

//    @Builder
//    private Users(int userId, String nickname, Email email, Password passwd) {
//        this.userId = userId;
//        this.email = email;
//        this.nickname = nickname;
//        this.passwd = passwd;
//    }
    @Column(length = 50, nullable = false)
    private String passwd;


    @Builder
    private Users(String userId, String nickname, Email email, String passwd) {
        this.userId = userId;
        this.email = email;
        this.nickname = nickname;
        this.passwd = passwd;
    }

}
