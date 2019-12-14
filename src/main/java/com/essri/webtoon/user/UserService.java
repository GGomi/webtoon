package com.essri.webtoon.user;


import com.essri.webtoon.database.entity.UserToken;
import com.essri.webtoon.database.entity.Users;
import com.essri.webtoon.database.repository.UserRepository;
import com.essri.webtoon.database.repository.UserTokenRepository;
import com.essri.webtoon.web.request.KakaoApiClient;
import com.essri.webtoon.web.request.KakaoApiFactory;
import com.essri.webtoon.web.request.dto.KakaoApiProfileResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ToString
public class UserService {

    private final UserRepository userRepository;
    private KakaoApiClient kakaoApiClient;
    private final UserTokenRepository userTokenRepository;

    public UserService(UserRepository userRepository, UserTokenRepository userTokenRepository) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
    }

    public UsersDTO.Res signUp(UsersDTO.SignUpReq dto) {
        Users user = userRepository.save(dto.toEntity());

        userTokenRepository.save(UserToken.builder()
                .userId(dto.getUserId())
                .token(dto.getToken())
                .refreshToken(dto.getRefreshToken())
                .build());

        return new UsersDTO.Res(user);
    }

    public KakaoApiProfileResponse getProfile(String token) {
        kakaoApiClient = KakaoApiFactory.newInstance(token).newClient();

        // request exception 체크해야함
        return kakaoApiClient.getProfile().blockingGet();
    }

    public UsersDTO.Profile checkingJoined(Long userId, String token, String refreshToken) {
        Users user = userRepository.findByUserId(userId).orElseGet(() -> Users.builder()
                .username("")
                .build());

        if (!user.getUsername().isEmpty()) {
            userTokenRepository.save(
                    UserToken.builder()
                            .userId(userId)
                            .token(token)
                            .refreshToken(refreshToken)
                            .build()
            );
        }

        return new UsersDTO.Profile(userId, user.getUsername());
    }
}
