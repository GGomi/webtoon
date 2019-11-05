package com.essri.webtoon.database.repository;

import com.essri.webtoon.database.entity.Toons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToonRepository extends JpaRepository<Toons, String> {
}
