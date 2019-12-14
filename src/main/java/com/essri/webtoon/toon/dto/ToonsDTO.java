package com.essri.webtoon.toon.dto;

import com.essri.webtoon.database.entity.Toons;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ToonsDTO {
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ConvertWebToonLists {

        @NotEmpty
        private String name;

        @NotEmpty
        private String[] serializeDay;

        @NotEmpty
        private String provider;

        @NotEmpty
        private String href;

        @NotEmpty
        private String imgSrc;

        @Builder
        public ConvertWebToonLists(String name, String[] serializeDay, String provider, String href, String imgSrc) {
            this.name = name;
            this.serializeDay = serializeDay;
            this.provider = provider;
            this.href = href;
            this.imgSrc = imgSrc;
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ListRes {
        @NotEmpty
        private String code;

        @NotEmpty
        private String name;

        @NotEmpty
        private byte serializeDay;

        @NotEmpty
        private String provider;

        @NotEmpty
        private String href;

        @NotEmpty
        private String imgSrc;

        public ListRes(Toons toon) {
            this.code = toon.getCode();
            this.name = toon.getName();
            this.serializeDay = toon.getSerializeDay();
            this.provider = toon.getProvider();
            this.href = toon.getHref();
            this.imgSrc = toon.getImgSrc();
        }
    }

}
