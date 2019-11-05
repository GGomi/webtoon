package com.essri.webtoon.web.dto;

import com.essri.webtoon.database.entity.Email;
import com.essri.webtoon.database.entity.Users;
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
        private String username;

        private String password;

        @Valid
        private Email email;

        @Builder
        public SignUpReq(String username, String password, Email email, int user_id) {
            this.email = email;
            this.username = username;
            this.password = password;
            this.user_id = user_id;
        }

        /**
         * TODO toEntity() 작성.......
         */
        public Users toEntity() {
            return Users.builder()
                    .email(this.email.getValue())
                    .username(this.username)
                    .password(this.password)
                    .build();
//                    .passwd(Password.builder().value(this.passwd).build())

        }
    }

    @Getter
    @ToString
    public static class Res {
        private Long user_id;
        private Email email;
        private String username;
        private String password;

        public Res(Users user) {
            this.user_id = user.getUserId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.password = user.getPassword().getValue();
        }
    }
}
