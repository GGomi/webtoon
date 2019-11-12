package com.essri.webtoon.user;


import com.essri.webtoon.database.repository.UserRepository;
import com.essri.webtoon.database.entity.Users;
import com.essri.webtoon.web.request.KakaoApiClient;
import com.essri.webtoon.web.request.KakaoApiFactory;
import com.essri.webtoon.web.request.dto.KakaoApiTokenResponse;
import com.essri.webtoon.web.request.dto.KakaoTokenRequest;
import com.essri.webtoon.web.request.impl.KakaoApiServiceGenerator;
import lombok.AllArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ToString
public class UserService {

    private final UserRepository userRepository;
    private final KakaoApiClient kakaoApiClient;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        kakaoApiClient = KakaoApiFactory.newInstance().newClient();
    }

    /**
     * TODO UserService(첫번째 유저정보 저장)
     * 1. 유저아이디 생성은 어떻게 할 것 인가?-------> ??
     * 2. OAuth 사용?                         -------> 아직 미사용
     * 3. SpringBoot Security는 고려 중...... -------> 패스워드 인코딩부분에서 사용
     */


    public Users create(UsersDTO.SignUpReq dto) {
        return userRepository.save(dto.toEntity());
    }

    public void getInputUserInfo(Users user) {

    }

    public String getToken(final KakaoTokenRequest request) {

        String token = kakaoApiClient.getToken(request)
                .map(KakaoApiTokenResponse::getAccessToken)
                .blockingGet();

        log.info(token);
        return token;
    }
}
