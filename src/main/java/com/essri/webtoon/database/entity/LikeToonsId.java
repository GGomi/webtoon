package com.essri.webtoon.database.entity;

import com.essri.webtoon.liketoon.LikeToonDto;
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


    @Column(name = "toon_code")
    private String toonCode;

    @Column(name = "user_id")
    private long userId;

    @Builder
    public LikeToonsId(String toonCode, long userId) {
        this.userId = userId;
        this.toonCode = toonCode;
    }
    static public LikeToonsId convertDtoToId(LikeToonDto likeToonDto) {
        return LikeToonsId.builder().userId(likeToonDto.getUserId()).toonCode(likeToonDto.getToonCode()).build();
    }
}
