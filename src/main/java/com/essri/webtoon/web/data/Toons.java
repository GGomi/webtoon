package com.essri.webtoon.web.data;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="toons")
public class Toons {

    @Id
    @Column(length = 100, nullable = false)
    private String toon_code;

    @Column(length = 100, nullable = false)
    private String toon_name;

    @Column(length = 100 , nullable = false)
    private byte serialize_day;

    @Column(length = 100, nullable = false)
    private String toon_provider;

    @Column(length = 300, nullable = false)
    private String toon_href;

    @Column(length = 500, nullable = false)
    private String toon_imgsrc;

    public void setSerialize_day(byte serialize_day) {
        this.serialize_day = serialize_day;
    }

    @Builder
    public Toons(String toon_code, String toon_name, byte serialize_day, String toon_provider, String toon_href, String toon_imgsrc) {
        this.toon_code      = toon_code;
        this.toon_name      = toon_name;
        this.serialize_day  = serialize_day;
        this.toon_provider  = toon_provider;
        this.toon_href      = toon_href;
        this.toon_imgsrc    = toon_imgsrc;
    }
}
