package com.essri.webtoon.web.controller;

import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.service.UserService;
import com.essri.webtoon.web.dto.ToonsDTO;
import com.essri.webtoon.web.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/webtoon")
public class WebtoonRestController {

    private final ToonService toonservice;
    private final UserService userService;

    /**
     * 웹툰 리스트 불러오기
     * @return toons 테이블 (entity: ConvertWebToonLists)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @ResponseStatus(value = HttpStatus.OK)
    public Map<String, Map<String, List<ToonsDTO.ConvertWebToonLists>>> getConvertList() {
        return toonservice.convertList();
    }

    /**
     * TODO 회원가입 기능 작성중..
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsersDTO.Res userJoin(@RequestBody @Valid final UsersDTO.SignUpReq dto) {
        UsersDTO.Res res = new UsersDTO.Res(userService.create(dto));
        log.debug(res.toString());
        return res;
    }

}
