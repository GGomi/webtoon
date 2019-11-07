package com.essri.webtoon.web.controller;

import com.essri.webtoon.service.UserService;
import com.essri.webtoon.web.dto.UsersDTO;
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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UsersDTO dto) {

        return null;
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
