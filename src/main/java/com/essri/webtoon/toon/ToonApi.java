package com.essri.webtoon.toon;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.essri.webtoon.web.dto.BaseRestResponse.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/webtoon")
public class ToonApi {

    private final ToonService toonservice;

    @GetMapping("/test")
    public String test() {
        toonservice.crawlNaverData();
        toonservice.crawlDaumData();
        return "success";
    }

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
