package com.essri.webtoon.toon;

import com.essri.webtoon.config.CrawlingConst;
import com.essri.webtoon.toon.dto.ToonsDTO;
import com.essri.webtoon.toon.model.daum.Datum;
import com.essri.webtoon.toon.model.daum.DaumRestTemplate;
import com.essri.webtoon.database.repository.ToonRepository;
import com.essri.webtoon.database.entity.Toons;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.essri.webtoon.config.CrawlingConst.NAVER_END_POINT;
import static com.essri.webtoon.config.CrawlingConst.NAVER_WEBTOON_LIST_TAG;

@Service
@Slf4j
public class ToonCrawlService {

    private final ToonRepository toonRepository;
    private final RestTemplate restTemplate;

    private final String[] dayList = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private HashMap<String, Byte> map;
    private byte pow = 1;

    public ToonCrawlService(ToonRepository toonRepository, RestTemplate restTemplate) {
        this.toonRepository = toonRepository;
        this.restTemplate = restTemplate;
        this.map = new HashMap<>();

        for (String a : dayList) {
            map.put(a, pow);
            pow *= 2;
        }
    }

    public List<Toons> crawlNaverData() {
        // Exception 필요

        // 추후 따로 전역으로 따로 빼서 쓰는게 좋을 듯
        boolean flag = true;
        String provider = "NAVER";
        // 크롤링한 데이터를 담을 List
        List<Toons> info = new ArrayList<>();

        try {
            // NAVER 요일별 웹툰 리스트 크롤링 (provider : https://comic.naver.com)
            Document doc = Jsoup.connect(NAVER_END_POINT).get();
            Elements contents = doc.select(NAVER_WEBTOON_LIST_TAG);

            for (Element e : contents) {
                String dayInfo = e.className().toUpperCase();
                Elements a = e.nextElementSibling().select(".thumb");

                for (Element i : a) {
                    /*
                     * code         =>      웹툰 고유코드
                     * provider     =>      웹툰 플랫폼
                     * dayInfo      =>      웹툰 연재일
                     * href         =>      웹툰 링크
                     * src          =>      웹툰 썸네일 이미지 링크
                     * name         =>      웹툰 이름
                     */

                    Element aTag = i.selectFirst("a");
                    Element img = aTag.selectFirst("img");
                    String href = aTag.attr("href");
                    String src = img.attr("src");
                    String name = img.attr("title");
                    String code = "";
                    byte serial = map.get(dayInfo);
                    Pattern p = Pattern.compile("(\\d+\\d)");
                    Matcher m = p.matcher(href);

                    // 정규식에 맞는 부분 찾기
                    while (m.find()) {
                        code = m.group();
                    }

                    // Error Exception

                    // 연재일이 하루가 아닌 웹툰들을 위해 OR연산을 통해 데이터 생성
                    if (info.size() != 0) {
                        for (Toons t : info) {
                            if (t.getCode().equals(code)) {
                                serial = (byte) (t.getSerializeDay() | serial);
                                t.setSerializeDay(serial);
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag) {
                        info.add(Toons.builder()
                                .code(code)
                                .name(name)
                                .serializeDay(serial)
                                .provider(provider)
                                .href(href)
                                .imgSrc(src)
                                .build());
                    } else {
                        flag = true;
                    }
                }
            }
        } catch (IOException e) {
            log.debug(e.getMessage());
            return null;
        }
        return toonRepository.saveAll(info);
    }

    public List<Toons> crawlDaumData() {

        Map<String, Toons> toonsMap = Maps.newHashMap();


        for (String day : dayList) {
            DaumRestTemplate t = restTemplate.getForObject(CrawlingConst.DAUM_END_POINT + day.toLowerCase(), DaumRestTemplate.class);

            if (t != null && t.getData().size() > 0) {
                for (Datum datum : t.getData()) {
                    String toonId = datum.getId().toString();

                    if (toonsMap.containsKey(toonId)) {
                        Toons toon = toonsMap.get(toonId);
                        byte serial = map.get(day);
                        toon.setSerializeDay((byte) (toon.getSerializeDay() | serial));
                        toonsMap.put(toonId, toon);
                    } else {
                        String thumbnailHref = datum.getThumbnailImage() != null ? datum.getThumbnailImage().getUrl() : datum.getThumbnailImage2().getUrl();
                        ToonsDTO.ConvertWebToonLists c = ToonsDTO.ConvertWebToonLists.builder().build();
                        toonsMap.put(toonId,
                                Toons.builder()
                                        .code(toonId)
                                        .href(CrawlingConst.DAUM_WEBTOON_END_POINT + datum.getNickname())
                                        .imgSrc(thumbnailHref)
                                        .name(datum.getTitle())
                                        .provider("DAUM")
                                        .serializeDay(map.get(day))
                                        .build()
                        );
                    }
                }
            }

        }

        return toonRepository.saveAll(Collections.list(Collections.enumeration(toonsMap.values())));
    }

    public List<ToonsDTO.ListRes> getList() {
        return toonRepository.findAll().stream()
                .map(ToonsDTO.ListRes::new)
                .collect(Collectors.toList());
    }


}
