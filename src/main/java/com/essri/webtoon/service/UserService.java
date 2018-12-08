package com.essri.webtoon.service;


import com.essri.webtoon.web.data.UserRepository;
import com.essri.webtoon.web.data.Users;
import com.essri.webtoon.web.dto.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    /**
     * TODO UserService(첫번째 유저정보 저장)
     * 1. 유저아이디 생성은 어떻게 할 것 인가?
     * 2. OAuth 사용?
     * 3. SpringBoot Security는 고려 중......
     */
    private final UserRepository userRepository;

    public Users create(UsersDTO.SignUpReq dto) {
        return userRepository.save(dto.toEntity());
    }
    public void getInputUserInfo(Users user) {

    }
}
