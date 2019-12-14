package com.essri.webtoon.database.repository;

import com.essri.webtoon.database.entity.LikeToons;
import com.essri.webtoon.database.entity.LikeToonsId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeToonRepository extends JpaRepository<LikeToons, LikeToonsId> {
}
