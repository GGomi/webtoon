package com.essri.webtoon.web.dto;

import com.essri.webtoon.web.data.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}