package com.essri.webtoon.ServiceTests;

import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.web.data.ToonRepository;
import com.essri.webtoon.web.data.Toons;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest
public class ToonServiceTest {

    @Autowired
    private ToonService toonService;

    @Autowired
    private ToonRepository toonRepository;

    @After
    public void cleanup() {
        toonRepository.deleteAll();
    }

    @Test
    public void webtoon_테이블에_데이터저장 () {
        // given

        Toons info = Toons.builder()
                                .toon_code("CODE")
                                .toon_name("testName")
                                .serialize_day("mon")
                                .toon_href("www.www")
                                .toon_imgsrc("img")
                                .toon_provider("NAVER")
                                .build();

//        toonService.saveData(list);
        toonRepository.save(info);

        Toons toon = toonRepository.findAll().get(0);
        assertThat(toon.getToon_name()).isEqualTo(info.getToon_name());
        assertThat(toon.getSerialize_day()).isEqualTo(info.getSerialize_day());
        assertThat(toon.getToon_href()).isEqualTo(info.getToon_href());
        assertThat(toon.getToon_imgsrc()).isEqualTo(info.getToon_imgsrc());
        assertThat(toon.getToon_provider()).isEqualTo(info.getToon_provider());
    }
    @Test
    public void 네이버_웹툰_크롤링_테스트() {
        // json 출력을 위한 임시코드
        // given
        List<Toons> list = toonService.crawlData();

        // when

        // then
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(list.toString(),Object.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void 다음_웹툰_크롤링_테스트() {
        // given

        // when

        // then
    }
}

