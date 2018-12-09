package com.essri.webtoon.web.dto;

import com.essri.webtoon.model.Email;
import com.essri.webtoon.model.Password;
import com.essri.webtoon.web.data.Users;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

public class UsersDTO {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {

        @NotEmpty
        private String nickname;

        @NotEmpty
        private String userId;

        private String passwd;

        @Valid
        private Email email;

        @Builder
        public SignUpReq (String nickname, String passwd, Email email, String userId) {
            this.email      = email;
            this.nickname   = nickname;
            this.passwd     = passwd;
            this.userId     = userId;
        }

        /**
         * TODO toEntity() 작성.......
         */
        public Users toEntity() {
            return Users.builder()
                    .email(this.email)
                    .nickname(this.nickname)
//                    .passwd(Password.builder().value(this.passwd).build())
                    .passwd(this.passwd)
                    .userId(this.userId)
                    .build();
        }
    }

    @Getter
    public static class Res {
        private Email email;
        private String nickname;
        private String userId;
        private String passwd;
//        private Password passwd;

        public Res(Users user) {
            this.email      = user.getEmail();
            this.nickname   = user.getNickname();
            this.passwd     = user.getPasswd();
            this.userId     = user.getUserId();
        }
    }
}
