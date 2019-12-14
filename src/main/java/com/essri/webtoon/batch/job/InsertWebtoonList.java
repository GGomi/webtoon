package com.essri.webtoon.batch.job;

import com.essri.webtoon.toon.ToonCrawlService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class InsertWebtoonList {

    private final ToonCrawlService toonCrawlService;

//    @Scheduled(cron = "* * 9 * * *")
    public void insertWebtoonList() {
        toonCrawlService.crawlNaverData();
        toonCrawlService.crawlDaumData();
    }

}
