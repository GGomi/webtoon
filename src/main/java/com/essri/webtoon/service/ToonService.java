package com.essri.webtoon.service;

import com.essri.webtoon.web.data.ToonRepository;
import com.essri.webtoon.web.data.Toons;
import com.essri.webtoon.web.dto.ToonsDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
//@Transactional
@Slf4j
public class ToonService {

    private final ToonRepository toonRepository;

    public List<Toons> crawlData() {
        // Exception 필요
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
                     * code         =>      웹툰 고유코드
                     * provider     =>      웹툰 플랫폼
                     * dayInfo      =>      웹툰 연재일
                     * href         =>      웹툰링크
                     * src          =>      웹툰 썸네일 이미지 링크
                     * name         =>      웹툰 이름
                     */
                    Element       aTag            = i.selectFirst("a");
                    Element       img             = aTag.selectFirst("img");
                    String        href            = aTag.attr("href");
                    String        src             = img.attr("src");
                    String        name            = img.attr("title");
                    String        code            = String.valueOf(dayInfo.substring(0,2)).toUpperCase();
                    Pattern       p               = Pattern.compile("(\\d+\\d)");
                    Matcher       m               = p.matcher(href);

                    while(m.find()) {
                        code = code.concat(m.group());
                    }

                    info.add(Toons.builder()
                            .toon_code(code)
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
        log.info(":::::::::::::::::::::::::ToonService.crawlData():::::::::::::::::::::::::>>>>>> SUCCESS!!!");
        return toonRepository.saveAll(info);
    }

    public List<ToonsDTO.ListRes> getList() {
        List<Toons> lists = toonRepository.findAll();
        List<ToonsDTO.ListRes> nList = new ArrayList<>();

        for(Toons t : lists) {
            nList.add(new ToonsDTO.ListRes(t));
        }

        if(!nList.isEmpty()) {
            log.info(":::::::::::::::::::::::::ToonService.getList():::::::::::::::::::::::::>>>>>> SUCCESS!!!");
        } else {
            log.error(":::::::::::::::::::::::::ToonService.getList():::::::::::::::::::::::::>>>>>> ERROR!!!");
        }

        return nList;
    }
}
