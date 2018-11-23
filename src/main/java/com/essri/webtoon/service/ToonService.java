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
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@AllArgsConstructor
@Slf4j
public class ToonService {

    private final ToonRepository toonRepository;
    private final String[] dayList = {"MON","TUE","WED","THU","FRI","SAT","SUN"};

    public List<Toons> crawlData() {
        HashMap<String, Byte> map = new HashMap<>();
        byte pow = 1;

        for(String a : dayList) {
            map.put(a,pow);
            pow *= 2;
        }

        // Exception 필요

        // 추후 따로 전역으로 따로 빼서 쓰는게 좋을 듯
        boolean      flag        = true;
        String       provider    = "NAVER";
        // 크롤링한 데이터를 담을 List
        List<Toons>  info        = new ArrayList<>();

        try {
            // NAVER 요일별 웹툰 리스트 크롤링 (provider : https://comic.naver.com)
            Document doc             = Jsoup.connect("https://comic.naver.com/webtoon/weekday.nhn").get();
            Elements contents        = doc.select(".col_inner h4");

            for(Element e:contents) {
                String      dayInfo         = e.className().toUpperCase();
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
                    String        code            = "";
                    byte          serial          = map.get(dayInfo);
                    Pattern       p               = Pattern.compile("(\\d+\\d)");
                    Matcher       m               = p.matcher(href);

                    // 정규식에 맞는 부분 찾기
                    while(m.find()) {
                        code = m.group();
                    }

                    // Error Exception

                    // 연재일이 하루가 아닌 웹툰들을 위해 OR연산을 통해 데이터 생성
                    if(info.size() != 0) {
                        for(Toons t : info) {
                            if(t.getToon_code().equals(code)) {
                                serial = (byte)(t.getSerialize_day()|serial);
                                t.setSerialize_day(serial);
                                flag = false;
                                break;
                            }
                        }
                    }

                    if(flag) {
                        info.add(Toons.builder()
                                .toon_code(code)
                                .toon_name(name)
                                .serialize_day(serial)
                                .toon_provider(provider)
                                .toon_href(href)
                                .toon_imgsrc(src)
                                .build());
                    } else {
                        flag = true;
                    }
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

    public HashMap<String, List<ToonsDTO.ConvertWebToonLists>> convertList() {
        HashMap<Byte, String> map       = new HashMap<>();
        byte[]                arr       = {64, 32, 16, 8, 4, 2, 1};
        byte                  pow       = 1;

        for(String a : dayList) {
            map.put(pow,a);
            pow *= 2;
        }
        // 전체 테이블데이터를 불러와서 클라이언트로 넘겨줄 새로운 모델로 변형
        List<Toons> lists = toonRepository.findAll();
        HashMap<String, List<ToonsDTO.ConvertWebToonLists>> dayMap = new HashMap<>();


        for(Toons t : lists) {

            byte serial = t.getSerialize_day();
            List<String> tempList = new ArrayList<>();

            for(byte num : arr) {
                if(serial >= num) {
                    String key = map.get(num);
                    tempList.add(key);
                    serial = (byte)(serial - num);
                }
            }

            String[] array      = new String[tempList.size()];
            array               = tempList.toArray(array);

            ToonsDTO.ConvertWebToonLists toon =
            ToonsDTO.ConvertWebToonLists.builder()
                                        .toon_name(t.getToon_name())
                                        .serialize_day(array)
                                        .toon_href(t.getToon_href())
                                        .toon_imgsrc(t.getToon_imgsrc())
                                        .toon_provider(t.getToon_provider())
                                        .build();
            for(String a : tempList) {
                List<ToonsDTO.ConvertWebToonLists> innerList = new ArrayList<>();
                if(dayMap.get(a) != null) {
                    innerList = dayMap.get(a);
                }
                innerList.add(toon);
                log.debug(a);
                dayMap.put(a, innerList);
            }
        }
        log.debug("ToonService | >>>>>>>>>>>>>> convertList()::::::::::::::::\n " + dayMap);
        return dayMap;
    }
}
