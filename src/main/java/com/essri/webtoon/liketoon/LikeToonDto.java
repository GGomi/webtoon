package com.essri.webtoon.liketoon;

import com.essri.webtoon.database.entity.Toons;
import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@ToString
@NoArgsConstructor
public class LikeToonDto {

    private String toonCode;
    private long userId;

    @Builder
    public LikeToonDto(String toonCode, long userId) {
        this.toonCode = toonCode;
        this.userId = userId;
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class LikeToonReq {

        @NotEmpty
        private String toonCode;

        @Builder
        public LikeToonReq(@NotEmpty String toonCode) {
            this.toonCode = toonCode;
        }
        public LikeToonDto convertReqtoDto(long userId){
           return LikeToonDto.builder().toonCode(this.toonCode).userId(userId).build();
        }
    }
}
