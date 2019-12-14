package com.essri.webtoon.database.repository;

import com.essri.webtoon.database.entity.LikeToons;
import com.essri.webtoon.database.entity.LikeToonsId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeToonRepository extends JpaRepository<LikeToons, LikeToonsId> {
    List<LikeToons> findByLikeToonsId_UserId(Long userId);
}
