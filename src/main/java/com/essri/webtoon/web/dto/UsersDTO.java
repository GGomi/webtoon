package com.essri.webtoon.web.dto;

import com.essri.webtoon.entity.Email;
import com.essri.webtoon.web.data.Users;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@ToString
public class UsersDTO {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {

        private int user_id;

        @NotEmpty
        private String nickname;

        private String passwd;

        @Valid
        private Email email;

        @Builder
        public SignUpReq (String nickname, String passwd, Email email, int user_id) {
            this.email      = email;
            this.nickname   = nickname;
            this.passwd     = passwd;
            this.user_id     = user_id;
        }

        /**
         * TODO toEntity() 작성.......
         */
        public Users toEntity() {
            return Users.builder()
                    .email(this.email)
                    .nickname(this.nickname)
                    .passwd(this.passwd)
                    .user_id(this.user_id)
                    .build();
            //      .passwd(Password.builder().value(this.passwd).build())
        }
    }

    @Getter
    @ToString
    public static class Res {
        private Email email;
        private String nickname;
        private int user_id;
        private String passwd;
//        private Password passwd;

        public Res(Users user) {
            this.email      = user.getEmail();
            this.nickname   = user.getNickname();
            this.passwd     = user.getPasswd();
            this.user_id     = user.getUser_id();
        }
    }
}
