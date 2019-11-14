package com.essri.webtoon.user;


import com.essri.webtoon.database.repository.UserRepository;
import com.essri.webtoon.database.entity.Users;
import com.essri.webtoon.web.request.KakaoApiClient;
import com.essri.webtoon.web.request.KakaoApiFactory;
import com.essri.webtoon.web.request.dto.KakaoApiProfileResponse;
import com.essri.webtoon.web.request.dto.KakaoTokenRequest;
import com.essri.webtoon.web.request.dto.UserResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@ToString
public class UserService {

    private final UserRepository userRepository;
    private KakaoApiClient kakaoApiClient;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * TODO UserService(첫번째 유저정보 저장)
     * 1. 유저아이디 생성은 어떻게 할 것 인가?-------> ??
     * 2. OAuth 사용?                         -------> 아직 미사용
     * 3. SpringBoot Security는 고려 중...... -------> 패스워드 인코딩부분에서 사용
     */

    public UsersDTO.Res create(UsersDTO.SignUpReq dto) {
        return new UsersDTO.Res(userRepository.save(dto.toEntity()));
    }

    public KakaoApiProfileResponse getProfile(String token) {
        kakaoApiClient = KakaoApiFactory.newInstance(token).newClient();

        // request exception 체크해야함
        return kakaoApiClient.getProfile().blockingGet();
    }

    public UsersDTO.Profile checkingJoined(Long userId) {
        Users user = userRepository.findByUserId(userId).orElseGet(() -> Users.builder()
                .username("")
                .email("")
                .build());

        return new UsersDTO.Profile(userId, user.getUsername());
    }
}
