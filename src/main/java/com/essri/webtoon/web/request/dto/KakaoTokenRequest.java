package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class KakaoTokenRequest {

    @NotNull
    @JsonProperty("token")
    private String token;

    @NotNull
    @JsonProperty("refresh_token")
    private String refreshToken;

}
