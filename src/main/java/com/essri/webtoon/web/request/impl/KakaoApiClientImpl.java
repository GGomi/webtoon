package com.essri.webtoon.web.request.impl;

import com.essri.webtoon.web.request.KakaoApiClient;
import com.essri.webtoon.web.request.dto.KakaoApiTokenResponse;
import com.essri.webtoon.web.request.dto.KakaoTokenRequest;
import io.reactivex.Single;

public class KakaoApiClientImpl implements KakaoApiClient {
    private final KakaoApiService kakaoApiService;

    public KakaoApiClientImpl() {
        KakaoApiServiceGenerator kakaoApiServiceGenerator = new KakaoApiServiceGenerator();
        this.kakaoApiService = kakaoApiServiceGenerator.createService(KakaoApiService.class);
    }

    @Override
    public Single<KakaoApiTokenResponse> getToken(KakaoTokenRequest request) {
        return kakaoApiService.getToken(request);
    }
}
