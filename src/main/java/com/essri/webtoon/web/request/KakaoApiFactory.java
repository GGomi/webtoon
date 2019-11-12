package com.essri.webtoon.web.request;

import com.essri.webtoon.web.request.impl.KakaoApiClientImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoApiFactory {
    public static KakaoApiFactory newInstance() {
        return new KakaoApiFactory();
    }

    public KakaoApiClient newClient() {
        return new KakaoApiClientImpl();
    }
}
