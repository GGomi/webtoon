package com.essri.webtoon.web.request;

import com.essri.webtoon.web.request.dto.KakaoApiTokenResponse;
import com.essri.webtoon.web.request.dto.KakaoTokenRequest;
import io.reactivex.Single;

public interface KakaoApiClient {
     Single<KakaoApiTokenResponse> getToken(KakaoTokenRequest request);
}
