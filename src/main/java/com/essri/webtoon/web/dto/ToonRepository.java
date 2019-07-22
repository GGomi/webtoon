package com.essri.webtoon.web.dto;

import com.essri.webtoon.web.model.Toons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToonRepository extends JpaRepository<Toons, String> {
}
