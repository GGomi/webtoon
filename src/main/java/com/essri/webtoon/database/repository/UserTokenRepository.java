package com.essri.webtoon.database.repository;

import com.essri.webtoon.database.entity.UserToken;
import org.springframework.data.repository.CrudRepository;

public interface UserTokenRepository extends CrudRepository<UserToken, Long> {
}
