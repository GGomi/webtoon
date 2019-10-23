package com.essri.webtoon.web.dto;

import com.essri.webtoon.web.model.Toons;
import lombok.*;

import javax.validation.constraints.NotEmpty;

public class ToonsDTO {
    @Getter
    @ToString
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
    @ToString
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
            this.toon_code      = toon.getToonCode();
            this.toon_name      = toon.getToonName();
            this.serialize_day  = toon.getSerializeDay();
            this.toon_provider  = toon.getToonProvider();
            this.toon_href      = toon.getToonHref();
            this.toon_imgsrc    = toon.getToonImgsrc();
        }
    }

}
