package com.essri.webtoon.liketoon;

import com.essri.webtoon.config.CrawlingConst;
import com.essri.webtoon.database.entity.LikeToons;
import com.essri.webtoon.database.entity.LikeToonsId;
import com.essri.webtoon.database.entity.Toons;
import com.essri.webtoon.database.repository.LikeToonRepository;
import com.essri.webtoon.database.repository.ToonRepository;
import com.essri.webtoon.exception.BusinessException;
import com.essri.webtoon.exception.ErrorCode;
import com.essri.webtoon.toon.model.daum.Datum;
import com.essri.webtoon.toon.model.daum.DaumRestTemplate;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
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
public class LikeToonService {

    private final LikeToonRepository likeToonRepository;
    private final RestTemplate restTemplate;

    public LikeToonService(LikeToonRepository likeToonRepository, RestTemplate restTemplate) {
        this.likeToonRepository = likeToonRepository;
        this.restTemplate = restTemplate;
    }

    public LikeToons likeToon(LikeToonDto likeToonDto) {
        LikeToonsId likeToonsId = LikeToonsId.convertDtoToId(likeToonDto);
        try {
            LikeToons likeToons = likeToonRepository.save(LikeToons.builder().likeToonsId(likeToonsId).liked(true).build());
            return likeToons;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ALREADY_COMPLETED_TASK);
        }
    }
    public boolean unlikeToon(LikeToonDto likeToonDto) {
        LikeToonsId likeToonsId = LikeToonsId.convertDtoToId(likeToonDto);
        try {
            likeToonRepository.delete(LikeToons.builder().likeToonsId(likeToonsId).liked(true).build());
            return true;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ALREADY_COMPLETED_TASK);
        }
    }

    public List<LikeToons> getLikeToonsList(long userId) {
        return likeToonRepository.findByLikeToonsId_UserId(userId);
    }
}
