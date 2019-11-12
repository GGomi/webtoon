package com.essri.webtoon.web.request.impl;

import com.essri.webtoon.web.request.constant.KakaoConstant;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.SECONDS;

public class KakaoApiServiceGenerator {
    private OkHttpClient.Builder httpClient = new OkHttpClient
            .Builder()
            .connectTimeout(5, SECONDS)
            .readTimeout(5, SECONDS)
            .writeTimeout(5, SECONDS);

    private Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(KakaoConstant.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

    public <S> S createService(Class<S> serviceClass) {
        Retrofit retrofit = builder.build();
        return retrofit.create(serviceClass);
    }

    public static <T> T executeSync(Call<T> call) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return null;
            }
        } catch (IOException e) {
            throw null;
        }
    }
}
