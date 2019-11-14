package com.essri.webtoon.user;

import com.essri.webtoon.database.entity.Email;
import com.essri.webtoon.database.entity.Users;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@ToString
public class UsersDTO {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class SignUpReq {

        @NotNull
        private Long userId;

        @NotEmpty
        private String nickname;

//        @Valid
//        private Email email;

        @Builder
        public SignUpReq(Long userId, String nickname) {
            this.userId = userId;
//            this.email = email;
            this.nickname = nickname;
        }

        public Users toEntity() {
            return Users.builder()
                    .userId(this.userId)
                    .username(this.nickname)
//                    .email(this.email.getValue())
                    .build();
        }
    }

    @Getter
    @ToString
    public static class Res {
        private Long userId;
//        private Email email;
        private String username;

        public Res(Users user) {
            this.userId = user.getUserId();
//            this.email = user.getEmail();
            this.username = user.getUsername();
        }
    }

    @Getter
    @ToString
    public static class Profile {
        private Long userId;
        private String username;

        public Profile(Long userId, String username) {
            this.userId = userId;
            this.username = username;
        }
    }


}
