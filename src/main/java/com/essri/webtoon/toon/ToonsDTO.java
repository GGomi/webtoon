package com.essri.webtoon.toon;

import com.essri.webtoon.database.entity.Toons;
import lombok.*;

import javax.validation.constraints.NotEmpty;

public class ToonsDTO {
    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ConvertWebToonLists {

        @NotEmpty
        private String toonName;

        @NotEmpty
        private String[] serializeDay;

        @NotEmpty
        private String toonProvider;

        @NotEmpty
        private String toonHref;

        @NotEmpty
        private String toonImgsrc;

        @Builder
        public ConvertWebToonLists(String toonName, String[] serializeDay, String toonProvider, String toonHref, String toonImgsrc) {
            this.toonName = toonName;
            this.serializeDay = serializeDay;
            this.toonProvider = toonProvider;
            this.toonHref = toonHref;
            this.toonImgsrc = toonImgsrc;
        }
    }

    @Getter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ListRes {
        @NotEmpty
        private String toonCode;

        @NotEmpty
        private String toonName;

        @NotEmpty
        private byte serializeDay;

        @NotEmpty
        private String toonProvider;

        @NotEmpty
        private String toonHref;

        @NotEmpty
        private String toonImgsrc;

        public ListRes(Toons toon) {
            this.toonCode = toon.getToonCode();
            this.toonName = toon.getToonName();
            this.serializeDay = toon.getSerializeDay();
            this.toonProvider = toon.getToonProvider();
            this.toonHref = toon.getToonHref();
            this.toonImgsrc = toon.getToonImgsrc();
        }
    }

}
