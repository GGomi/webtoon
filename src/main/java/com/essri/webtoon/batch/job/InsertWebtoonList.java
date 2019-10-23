package com.essri.webtoon.batch.job;

import com.essri.webtoon.service.ToonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsertWebtoonList {

    private final ToonService toonService;

//    @Scheduled(cron = "* * 9 * * *")
    public void insertWebtoonList() {
        toonService.crawlNaverData();
        toonService.crawlDaumData();
    }

}
