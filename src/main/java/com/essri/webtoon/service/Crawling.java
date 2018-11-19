package com.essri.webtoon.service;

import com.essri.webtoon.web.data.ToonInfo;
import com.essri.webtoon.web.data.Toons;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class Crawling {
    /**
     * 네이버 요일별 웹툰 목록 크롤링
     * @return
     */

    public List<Toons> getNaverToonData() {

        // 추후 따로 전역으로 따로 빼서 쓰는게 좋을 듯
        String          provider    = "NAVER";
        // 크롤링한 데이터를 담을 List
        List<Toons>  info        = new ArrayList<>();

        try {
            // NAVER 요일별 웹툰 리스트 크롤링 (provider : https://comic.naver.com)
            Document doc             = Jsoup.connect("https://comic.naver.com/webtoon/weekday.nhn").get();
            Elements contents        = doc.select(".col_inner h4");

            for(Element e:contents) {
                String      dayInfo         = e.className();
                Elements    a               = e.nextElementSibling().select(".thumb");

                for(Element i:a) {
                    /*
                     * provider     =>      웹툰 플랫폼
                     * dayInfo      =>      웹툰 연재일
                     * href         =>      웹툰링크
                     * src          =>      웹툰 썸네일 이미지 링크
                     * name         =>      웹툰 이름
                     */
                    Element         aTag            = i.selectFirst("a");
                    Element         img             = aTag.selectFirst("img");
                    String          href            = aTag.attr("href");
                    String          src             = img.attr("src");
                    String          name            = img.attr("title");

                    info.add(Toons.builder()
                            .toon_name(name)
                            .serialize_day(dayInfo)
                            .toon_provider(provider)
                            .toon_href(href)
                            .toon_imgsrc(src)
                            .build());
                }
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
        }

        return info;
    }
}
