package com.essri.webtoon.liketoon;

import com.essri.webtoon.database.entity.LikeToons;
import com.essri.webtoon.database.entity.LikeToonsId;
import com.essri.webtoon.database.repository.LikeToonRepository;
import com.essri.webtoon.exception.BusinessException;
import com.essri.webtoon.exception.ErrorCode;
import com.essri.webtoon.liketoon.model.LikeType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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
            return likeToonRepository.save(LikeToons.builder().likeToonsId(likeToonsId).liked(LikeType.LIKE).build());
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ALREADY_COMPLETED_TASK);
        }
    }

    public boolean unlikeToon(LikeToonDto likeToonDto) {
        LikeToonsId likeToonsId = LikeToonsId.convertDtoToId(likeToonDto);
        try {
            likeToonRepository.delete(LikeToons.builder().likeToonsId(likeToonsId).liked(LikeType.UNLIKE).build());
            return true;
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.ALREADY_COMPLETED_TASK);
        }
    }

    public List<LikeToons> getLikeToonsList(long userId) {
        return likeToonRepository.findByLikeToonsId_UserId(userId);
    }

}
