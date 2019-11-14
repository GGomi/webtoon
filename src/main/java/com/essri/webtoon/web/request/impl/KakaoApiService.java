package com.essri.webtoon.web.request.impl;

import com.essri.webtoon.web.request.dto.KakaoApiProfileResponse;
import io.reactivex.Single;
import retrofit2.http.GET;

public interface KakaoApiService {
    @GET("/v2/user/me")
    Single<KakaoApiProfileResponse> getProfile();
}
