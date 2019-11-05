package com.essri.webtoon.database.repository;

import com.essri.webtoon.database.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, String> {
}
