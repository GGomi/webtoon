package com.essri.webtoon.service;

import com.essri.webtoon.database.entity.Toons;
import com.essri.webtoon.database.repository.ToonRepository;
import com.essri.webtoon.exception.ToonNotFoundException;
import com.essri.webtoon.toon.ToonFindService;
import com.essri.webtoon.toon.dto.ToonsDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ToonFindServiceTest {

    @InjectMocks
    private ToonFindService toonFindService;

    @Mock
    private ToonRepository toonRepository;

    private Toons toons;
    private List<Toons> toonsList = new ArrayList<>();

    @Before
    public void setUp() {
        toons = Toons.builder()
                .serializeDay((byte) 2) // 화요일만 연재
                .toonCode("10000")
                .toonImgsrc("href")
                .toonName("test")
                .toonProvider("NAVER")
                .toonHref("link")
                .build();

        toonsList.add(toons);
    }

    @Test
    public void findAll_존재() {
        //given
        given(toonRepository.findAll()).willReturn(toonsList);

        //when
        Map<String, Map<String, List<ToonsDTO.ConvertWebToonLists>>> res = toonFindService.convertList();

        //then
        assertThat(res.get(toons.getToonProvider()).size()).isEqualTo(1);
    }

    @Test(expected = ToonNotFoundException.class)
    public void findAll_존재하지않음() {
        //given
        given(toonRepository.findAll()).willReturn(new ArrayList<>());

        //when
        toonFindService.convertList();

        //then
    }
}
