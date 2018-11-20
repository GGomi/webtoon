package com.essri.webtoon.web.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToonRepository extends JpaRepository<Toons, String> {
}
