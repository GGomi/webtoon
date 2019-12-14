package com.essri.webtoon.user;

import com.essri.webtoon.web.dto.BaseRestResponse;
import com.essri.webtoon.web.request.dto.KakaoTokenRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserApi {

    private final UserService userService;

    @PostMapping("/kakao/profile")
    public ResponseEntity login(@RequestBody KakaoTokenRequest request) {
        Long id = userService.getProfile(request.getToken()).getId();

        return BaseRestResponse.success(userService.checkingJoined(id, request.getToken(), request.getRefreshToken()));
    }

    @PostMapping("/signup")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity userJoin(@RequestBody @Valid final UsersDTO.SignUpReq dto) {
        return BaseRestResponse.success(userService.signUp(dto));
    }
}
