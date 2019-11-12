package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Builder
@ToString
@Getter
public class KakaoTokenRequest {

    @NotNull
    @JsonProperty("grant_type")
    private String grantType;

    @NotNull
    @JsonProperty("client_id")
    private String clientId;

    @NotNull
    @JsonProperty("redirect_uri")
    private String redirectUri;

    @NotNull
    @JsonProperty("code")
    private String code;

}
