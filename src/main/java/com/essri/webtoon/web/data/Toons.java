package com.essri.webtoon.web.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="toons")
public class Toons {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 100, nullable = false)
    private String toon_name;

    @Column(length = 100 , nullable = false)
    private String serialize_day;

    @Column(length = 100, nullable = false)
    private String toon_provider;

    @Column(length = 300, nullable = false)
    private String toon_href;

    @Column(length = 500, nullable = false)
    private String toon_imgsrc;

    /**
     * @param toon_name
     * @param serialize_day
     * @param toon_provider
     * @param toon_href
     * @param toon_imgsrc
     */

    @Builder
    public Toons(String toon_name, String serialize_day, String toon_provider, String toon_href, String toon_imgsrc) {
        this.toon_name      = toon_name;
        this.serialize_day  = serialize_day;
        this.toon_provider  = toon_provider;
        this.toon_href      = toon_href;
        this.toon_imgsrc    = toon_imgsrc;
    }
}
