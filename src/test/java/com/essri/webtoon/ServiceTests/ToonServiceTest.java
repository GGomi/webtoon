package com.essri.webtoon.ServiceTests;

import com.essri.webtoon.service.ToonService;
import com.essri.webtoon.web.dto.ToonRepository;
import com.essri.webtoon.web.model.Toons;
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
@ActiveProfiles("local")
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
    public void webtoon_테이블에_데이터저장() {
        // given

        Toons info = Toons.builder()
                .toonCode("123456")
                .toonName("testName")
                .serializeDay((byte) 64)
                .toonHref("www.www")
                .toonImgsrc("img")
                .toonProvider("NAVER")
                .build();

        toonRepository.save(info);

        Toons toon = toonRepository.findAll().get(0);
        assertThat(toon.getToonName()).isEqualTo(info.getToonName());
        assertThat(toon.getSerializeDay()).isEqualTo(info.getSerializeDay());
        assertThat(toon.getToonHref()).isEqualTo(info.getToonHref());
        assertThat(toon.getToonImgsrc()).isEqualTo(info.getToonImgsrc());
        assertThat(toon.getToonProvider()).isEqualTo(info.getToonProvider());
    }

    @Test
    public void 네이버_웹툰_크롤링_테스트() {
        // json 출력을 위한 임시코드
        // given
        List<Toons> list = toonService.crawlNaverData();
        // when

        // then
        try {
            ObjectMapper mapper = new ObjectMapper();
            Object json = mapper.readValue(list.toString(), Object.class);
            System.out.println("RESULT::::::::::::::\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(json));
        } catch (IOException e) {
            System.out.println("ERROR:::::::::::::::::::::>" + e.getMessage());
        }
    }

    @Test
    public void 다음_웹툰_크롤링_테스트() {
        // given

        List<Toons> list = toonService.crawlDaumData();
        // when

        // then
    }
}

