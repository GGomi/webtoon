package com.essri.webtoon.service;

import com.essri.webtoon.config.CrawlingConst;
import com.essri.webtoon.web.dto.Datum;
import com.essri.webtoon.web.dto.DaumRestTemplate;
import com.essri.webtoon.web.dto.ToonRepository;
import com.essri.webtoon.web.dto.ToonsDTO;
import com.essri.webtoon.web.model.Toons;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.essri.webtoon.config.CrawlingConst.NAVER_END_POINT;
import static com.essri.webtoon.config.CrawlingConst.NAVER_WEBTOON_LIST_TAG;

@Service
@Slf4j
public class ToonService {

    private final ToonRepository toonRepository;
    private final RestTemplate restTemplate;

    private final String[] dayList = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
    private HashMap<String, Byte> map;
    private byte pow = 1;

    public ToonService(ToonRepository toonRepository, RestTemplate restTemplate) {
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
                            if (t.getToon_code().equals(code)) {
                                serial = (byte) (t.getSerialize_day() | serial);
                                t.setSerialize_day(serial);
                                flag = false;
                                break;
                            }
                        }
                    }

                    if (flag) {
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
            return null;
        }
        return toonRepository.saveAll(info);
    }

    public List<Toons> crawlDaumData() {

        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(10)
                .setMaxConnPerRoute(10)
                .build();

        Map<String, DaumRestTemplate> daumWebtoonList = new HashMap<>();
        Map<String, Toons> toonsMap = new HashMap<>();


        for (String day : dayList) {
            DaumRestTemplate t = restTemplate.getForObject(CrawlingConst.DAUM_END_POINT + day.toLowerCase(), DaumRestTemplate.class);

            if (t != null && t.getData().size() > 0) {
                for (Datum datum : t.getData()) {
                    String toonId = datum.getId().toString();

                    if (toonsMap.containsKey(toonId)) {
                        Toons toon = toonsMap.get(toonId);
                        byte serial = map.get(day);
                        toon.setSerialize_day((byte) (toon.getSerialize_day() | serial));
                        toonsMap.put(toonId, toon);
                    } else {
                        String thumbnailHref = datum.getThumbnailImage() != null ? datum.getThumbnailImage().getUrl() : datum.getThumbnailImage2().getUrl();

                        toonsMap.put(toonId,
                                Toons.builder()
                                .toon_code(toonId)
                                .toon_href(CrawlingConst.DAUM_WEBTOON_END_POINT + datum.getNickname())
                                .toon_imgsrc(thumbnailHref)
                                .toon_name(datum.getTitle())
                                .toon_provider("DAUM")
                                .serialize_day(map.get(day))
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

    public Map<String, Map<String, List<ToonsDTO.ConvertWebToonLists>>> convertList() {
        HashMap<Byte, String> map = new HashMap<>();
        byte[] arr = {64, 32, 16, 8, 4, 2, 1};
        byte pow = 1;

        for (String a : dayList) {
            map.put(pow, a);
            pow *= 2;
        }

        // 전체 테이블데이터를 불러와서 클라이언트로 넘겨줄 새로운 모델로 변형
        List<Toons> lists = toonRepository.findAll();

        Map<String, Map<String, List<ToonsDTO.ConvertWebToonLists>>> providerMap = new HashMap<>();

        providerMap.put("NAVER", new HashMap<>());
        providerMap.put("DAUM", new HashMap<>());

        for (Toons t : lists) {
            Map<String, List<ToonsDTO.ConvertWebToonLists>> dayMap = providerMap.get(t.getToon_provider());

            byte serial = t.getSerialize_day();
            List<String> tempList = new ArrayList<>();

            for (byte num : arr) {
                if (serial >= num) {
                    String key = map.get(num);
                    tempList.add(key);
                    serial = (byte) (serial - num);
                }
            }

            String[] array = new String[tempList.size()];
            array = tempList.toArray(array);

            ToonsDTO.ConvertWebToonLists toon =
                    ToonsDTO.ConvertWebToonLists.builder()
                            .toon_name(t.getToon_name())
                            .serialize_day(array)
                            .toon_href(t.getToon_href())
                            .toon_imgsrc(t.getToon_imgsrc())
                            .toon_provider(t.getToon_provider())
                            .build();

            for (String a : tempList) {
                List<ToonsDTO.ConvertWebToonLists> innerList = new ArrayList<>();
                if (dayMap.get(a) != null) {
                    innerList = dayMap.get(a);
                }
                innerList.add(toon);
                dayMap.put(a, innerList);
            }

            providerMap.put(t.getToon_provider(), dayMap);
        }

        return providerMap;
    }
}
