package com.essri.webtoon.web;

import com.essri.webtoon.service.Crawling;
import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.web.data.ToonInfo;
import com.essri.webtoon.web.data.Toons;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class WebRestController {

    private Crawling crawling;
    private ToonService toonservice;

    @GetMapping("/test")
    public String test() {
        return "It's test!!!";
    }

    @GetMapping("/getList")
    public void getList() {
        List<Toons> list = crawling.getNaverToonData();

        toonservice.saveData(list);

    }
}
