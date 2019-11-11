package com.essri.webtoon.user;

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

        private int userId;

        @NotEmpty
        private String username;

        private String password;

        @Valid
        private Email email;

        @Builder
        public SignUpReq(String username, String password, Email email, int userId) {
            this.userId = userId;
            this.email = email;
            this.username = username;
            this.password = password;
        }

        public Users toEntity() {
            return Users.builder()
                    .username(this.username)
                    .password(this.password)
                    .email(this.email.getValue())
                    .build();
        }
    }

    @Getter
    @ToString
    public static class Res {
        private Long userId;
        private Email email;
        private String username;
        private String password;

        public Res(Users user) {
            this.userId = user.getUserId();
            this.email = user.getEmail();
            this.username = user.getUsername();
            this.password = user.getPassword().getValue();
        }
    }
}
