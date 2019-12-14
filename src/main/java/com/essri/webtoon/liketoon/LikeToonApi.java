package com.essri.webtoon.liketoon;

import com.essri.webtoon.user.UsersDTO;
import com.essri.webtoon.web.dto.BaseRestResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.essri.webtoon.web.dto.BaseRestResponse.success;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/likeToon")
public class LikeToonApi {
    private final LikeToonService likeToonService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity likeToon(@Valid @RequestBody LikeToonDto.LikeToonReq req, @RequestHeader(value="userId") long userId) {
        LikeToonDto likeToonDto = LikeToonDto.builder().userId(userId).toonCode(req.getToonCode()).build();
        return BaseRestResponse.success(likeToonService.likeToon(likeToonDto));
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity unlikeToon(@Valid @RequestBody LikeToonDto.LikeToonReq req, @RequestHeader(value="userId") long userId) {
        LikeToonDto likeToonDto = LikeToonDto.builder().userId(userId).toonCode(req.getToonCode()).build();
        return BaseRestResponse.success(likeToonService.unlikeToon(likeToonDto));
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getLikeToonsList(@RequestHeader(value="userId") long userId) {
        return BaseRestResponse.success(likeToonService.getLikeToonsList(userId));
    }

}
