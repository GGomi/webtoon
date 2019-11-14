package com.essri.webtoon.web.request.impl;

import com.essri.webtoon.web.request.KakaoApiClient;
import com.essri.webtoon.web.request.dto.KakaoApiProfileResponse;
import io.reactivex.Single;

public class KakaoApiClientImpl implements KakaoApiClient {
    private final KakaoApiService kakaoApiService;

    public KakaoApiClientImpl(String token) {
        KakaoApiServiceGenerator kakaoApiServiceGenerator = new KakaoApiServiceGenerator();
        this.kakaoApiService = kakaoApiServiceGenerator.createService(KakaoApiService.class, token);
    }

    @Override
    public Single<KakaoApiProfileResponse> getProfile() {
        return kakaoApiService.getProfile();
    }
}
