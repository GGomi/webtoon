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

        @Builder
        public SignUpReq(Long userId, String nickname) {
            this.userId = userId;
            this.nickname = nickname;
        }

        public Users toEntity() {
            return Users.builder()
                    .userId(this.userId)
                    .username(this.nickname)
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
