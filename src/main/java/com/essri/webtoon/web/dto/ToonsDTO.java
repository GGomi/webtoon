package com.essri.webtoon.web.dto;

import com.essri.webtoon.web.data.Toons;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

public class ToonsDTO {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ConvertWebToonLists {

        @NotEmpty
        private String toon_name;

        @NotEmpty
        private String[] serialize_day;

        @NotEmpty
        private String toon_provider;

        @NotEmpty
        private String toon_href;

        @NotEmpty
        private String toon_imgsrc;

        @Builder
        public ConvertWebToonLists(String toon_name, String[] serialize_day, String toon_provider, String toon_href, String toon_imgsrc) {
            this.toon_name      = toon_name;
            this.serialize_day  = serialize_day;
            this.toon_provider  = toon_provider;
            this.toon_href      = toon_href;
            this.toon_imgsrc    = toon_imgsrc;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ListRes {
        @NotEmpty
        private String toon_code;

        @NotEmpty
        private String toon_name;

        @NotEmpty
        private byte serialize_day;

        @NotEmpty
        private String toon_provider;

        @NotEmpty
        private String toon_href;

        @NotEmpty
        private String toon_imgsrc;

        public ListRes(Toons toon) {
            this.toon_code      = toon.getToon_code();
            this.toon_name      = toon.getToon_name();
            this.serialize_day  = toon.getSerialize_day();
            this.toon_provider  = toon.getToon_provider();
            this.toon_href      = toon.getToon_href();
            this.toon_imgsrc    = toon.getToon_imgsrc();
        }
    }
}
