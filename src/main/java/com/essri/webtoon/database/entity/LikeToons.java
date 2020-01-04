package com.essri.webtoon.database.entity;

import com.essri.webtoon.liketoon.model.LikeType;
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
    @Enumerated(EnumType.STRING)
    private LikeType liked;

    @Builder
    public LikeToons(LikeToonsId likeToonsId, LikeType liked) {
        this.likeToonsId = likeToonsId;
        this.liked = liked;
    }
}
