package com.essri.webtoon.web.request;

import com.essri.webtoon.web.request.impl.KakaoApiClientImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KakaoApiFactory {
    private String token;

    public KakaoApiFactory(String token) {
        this.token = token;
    }

    public static KakaoApiFactory newInstance(String token) {
        return new KakaoApiFactory(token);
    }

    public static KakaoApiFactory newInstance() {
        return new KakaoApiFactory(null);
    }

    public KakaoApiClient newClient() {
        return new KakaoApiClientImpl(token);
    }
}
