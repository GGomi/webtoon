package com.essri.webtoon.database.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="like_toons")
@Getter
@Entity
public class LikeToons {

    @EmbeddedId
    private LikeToonsId likeToonsId;

    @Column(name = "liked")
    private Boolean liked;

    @Builder
    public LikeToons(LikeToonsId likeToonsId, Boolean liked) {
        this.likeToonsId = likeToonsId;
        this.liked = liked;
    }
}
