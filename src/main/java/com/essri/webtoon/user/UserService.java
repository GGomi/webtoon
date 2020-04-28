package com.essri.webtoon.user;


import com.essri.webtoon.common.JwtTokenUtil;
import com.essri.webtoon.config.security.JwtAuthenticationResponse;
import com.essri.webtoon.database.entity.UserToken;
import com.essri.webtoon.database.entity.Users;
import com.essri.webtoon.database.repository.UserRepository;
import com.essri.webtoon.database.repository.UserTokenRepository;
import com.essri.webtoon.user.model.ProviderConstants;
import com.essri.webtoon.web.request.KakaoApiClient;
import com.essri.webtoon.web.request.KakaoApiFactory;
import com.essri.webtoon.web.request.dto.KakaoApiProfileResponse;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@ToString
public class UserService {

    private final UserRepository userRepository;
    private KakaoApiClient kakaoApiClient;

    private final UserTokenRepository userTokenRepository;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public UserService(UserRepository userRepository, UserTokenRepository userTokenRepository, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.userTokenRepository = userTokenRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
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
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            ProviderConstants.KAKAO.concat(String.valueOf(user.getUserId())),
                            ProviderConstants.KAKAO.concat(String.valueOf(user.getUserId()))
                    ));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(ProviderConstants.KAKAO.concat(String.valueOf(user.getUserId())));
            final String token = jwtTokenUtil.generateToken(userDetails);

//            userPersistService.updateLastLoginAsync(userEntityOptional.get().getUserId());

//            loginHistoryPersistService.saveLoginHistory(request, userEntityOptional.get().getUserId(), LoginType.I, LoginResult.Y, null);

            return new JwtAuthenticationResponse(token);
        }

        return new UsersDTO.Profile(userId, user.getUsername());
    }
}
