package com.essri.webtoon.web.controller;

import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.service.UserService;
import com.essri.webtoon.web.dto.BaseRestResponse;
import com.essri.webtoon.web.dto.ToonsDTO;
import com.essri.webtoon.web.dto.UsersDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.essri.webtoon.web.dto.BaseRestResponse.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/webtoon")
public class WebtoonApi {

    private final ToonService toonservice;

    /**
     * 웹툰 리스트 불러오기
     * @return toons 테이블 (entity: ConvertWebToonLists)
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getConvertList() {
        return success(toonservice.convertList());
    }

}
