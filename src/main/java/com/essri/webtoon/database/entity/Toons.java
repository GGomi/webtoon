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
    private String toonCode;

    @Column(length = 100, nullable = false)
    private String toonName;

    @Column(length = 100 , nullable = false)
    private byte serializeDay;

    @Column(length = 100, nullable = false)
    private String toonProvider;

    @Column(length = 300, nullable = false)
    private String toonHref;

    @Column(length = 500, nullable = false)
    private String toonImgsrc;

    public void setSerializeDay(byte serializeDay) {
        this.serializeDay = serializeDay;
    }

    @Builder
    public Toons(String toonCode, String toonName, byte serializeDay, String toonProvider, String toonHref, String toonImgsrc) {
        this.toonCode = toonCode;
        this.toonName = toonName;
        this.serializeDay = serializeDay;
        this.toonProvider = toonProvider;
        this.toonHref = toonHref;
        this.toonImgsrc = toonImgsrc;
    }
}
