package com.essri.webtoon.web.data;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
public class ToonInfo {
    private String toon_name;
    private String serialize_day;
    private String toon_provider;
    private String toon_href;
    private String toon_imgsrc;

    public Toons toEntity() {
        return Toons.builder()
                    .toon_name(toon_name)
                    .serialize_day(serialize_day)
                    .toon_provider(toon_provider)
                    .toon_href(toon_href)
                    .toon_imgsrc(toon_imgsrc)
                    .build();
    }

    /**
     *
     * @param toon_name
     * @param serialize_day
     * @param toon_provider
     * @param toon_href
     * @param toon_imgsrc
     */
    @Builder
    public ToonInfo(String toon_name, String serialize_day, String toon_provider, String toon_href, String toon_imgsrc) {
        this.toon_name      = toon_name;
        this.serialize_day  = serialize_day;
        this.toon_provider  = toon_provider;
        this.toon_href      = toon_href;
        this.toon_imgsrc    = toon_imgsrc;
    }
}

