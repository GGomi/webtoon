package com.essri.webtoon.web.request;

import com.essri.webtoon.web.request.dto.KakaoApiProfileResponse;
import io.reactivex.Single;

public interface KakaoApiClient {
     Single<KakaoApiProfileResponse> getProfile();
}
