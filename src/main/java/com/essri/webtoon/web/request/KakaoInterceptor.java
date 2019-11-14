package com.essri.webtoon.web.request;


import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class KakaoInterceptor implements Interceptor {

    private String token;

    public KakaoInterceptor(String token) {
        this.token = token;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        String headerValue = "Bearer " + token;
        newRequestBuilder.addHeader("Authorization", headerValue);
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }
}
