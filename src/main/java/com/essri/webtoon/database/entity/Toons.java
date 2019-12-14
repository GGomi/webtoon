package com.essri.webtoon.database.entity;

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
    private String code;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(name="serialize_day", length = 100 , nullable = false)
    private byte serializeDay;

    @Column(length = 100, nullable = false)
    private String provider;

    @Column(length = 300, nullable = false)
    private String href;

    @Column(name="img_src", length = 500, nullable = false)
    private String imgSrc;

    public void setSerializeDay(byte serializeDay) {
        this.serializeDay = serializeDay;
    }

    @Builder
    public Toons(String code, String name, byte serializeDay, String provider, String href, String imgSrc) {
        this.code = code;
        this.name = name;
        this.serializeDay = serializeDay;
        this.provider = provider;
        this.href = href;
        this.imgSrc = imgSrc;
    }
}
