package com.essri.webtoon.user;

import com.essri.webtoon.database.entity.Users;
import lombok.*;

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
        private String username;

        private String token;

        private String refreshToken;

        @Builder
        public SignUpReq(Long userId, String username, String token, String refreshToken) {
            this.userId = userId;
            this.username = username;
            this.token = token;
            this.refreshToken = refreshToken;
        }

        public Users toEntity() {
            return Users.builder()
                    .userId(this.userId)
                    .username(this.username)
                    .build();
        }
    }

    @Getter
    @ToString
    public static class Res {
        private Long userId;
        private String username;

        public Res(Users user) {
            this.userId = user.getUserId();
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
