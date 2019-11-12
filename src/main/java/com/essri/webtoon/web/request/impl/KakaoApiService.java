package com.essri.webtoon.web.request.impl;

import com.essri.webtoon.web.request.dto.KakaoApiTokenResponse;
import com.essri.webtoon.web.request.dto.KakaoTokenRequest;
import io.reactivex.Single;
import retrofit2.http.POST;

public interface KakaoApiService {
    @POST("/oauth/token")
    Single<KakaoApiTokenResponse> getToken(KakaoTokenRequest request);
}
