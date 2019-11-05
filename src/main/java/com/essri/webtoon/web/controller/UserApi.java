package com.essri.webtoon.web.controller;

import com.essri.webtoon.web.dto.BaseRestResponse;
import com.essri.webtoon.web.dto.UsersDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {

    @PostMapping("/login")
    public BaseRestResponse login(@RequestBody UsersDTO dto) {
        return null;
    }
}
