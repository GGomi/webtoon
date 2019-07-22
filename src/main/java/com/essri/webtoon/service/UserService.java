package com.essri.webtoon.service;


import com.essri.webtoon.web.dto.UserRepository;
import com.essri.webtoon.web.model.Users;
import com.essri.webtoon.web.dto.UsersDTO;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
@ToString
public class UserService {

    /**
     * TODO UserService(첫번째 유저정보 저장)
     * 1. 유저아이디 생성은 어떻게 할 것 인가?-------> ??
     * 2. OAuth 사용?                         -------> 아직 미사용
     * 3. SpringBoot Security는 고려 중...... -------> 패스워드 인코딩부분에서 사용
     */
    private final UserRepository userRepository;

    public Users create(UsersDTO.SignUpReq dto) {
        return userRepository.save(dto.toEntity());
    }
    public void getInputUserInfo(Users user) {

    }
}
