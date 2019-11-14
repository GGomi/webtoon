package com.essri.webtoon.database.repository;

import com.essri.webtoon.database.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(Long userId);
}
