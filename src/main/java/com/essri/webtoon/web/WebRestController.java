package com.essri.webtoon.web;

import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.web.data.ToonInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class WebRestController {
    @Autowired
    ToonService toonService;

    @GetMapping("/test")
    public String test() {
        return "Its test!!!";
    }

    @GetMapping("/getList")
    public void getList() {
        try {
            List<ToonInfo> asd = toonService.getToonData();
        } catch (Exception e){
            log.debug(e.getMessage());
        }

    }
}
