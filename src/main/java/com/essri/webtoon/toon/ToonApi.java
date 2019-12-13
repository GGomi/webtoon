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

    private final ToonCrawlService toonCrawlService;
    private final ToonFindService toonFindService;

    @GetMapping("/test")
    public String test() {
        toonCrawlService.crawlNaverData();
        toonCrawlService.crawlDaumData();
        return "success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity getConvertList() {
        return success(toonFindService.convertList());
    }

}
