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

    @PostMapping("/kakao/getToken")
    public ResponseEntity login(@RequestBody @Valid KakaoTokenRequest request) {
        return BaseRestResponse.success(userService.getToken(request));
    }

    /**
     * TODO 회원가입 기능 작성중..
     *
     * @return
     */
    @PostMapping("/join")
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsersDTO.Res userJoin(@RequestBody @Valid final UsersDTO.SignUpReq dto) {
        UsersDTO.Res res = new UsersDTO.Res(userService.create(dto));
        return res;
    }
}
