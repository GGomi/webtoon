package com.essri.webtoon.database.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LikeToonsId implements Serializable {


    @Column(name = "user_id")
    private String userId;

    @Column(name = "toon_code")
    private long toonCode;

    @Builder
    public LikeToonsId(String userId, long toonCode) {
        this.userId = userId;
        this.toonCode = toonCode;
    }
}
