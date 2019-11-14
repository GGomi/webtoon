package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({
        "id",
        "properties",
        "kakao_account"
})
@Getter
@Setter
@ToString
public class KakaoApiProfileResponse {

    @JsonProperty("id")
    public Long id;
    @JsonProperty("properties")
    public KakaoPropertyField properties;
    @JsonProperty("kakao_account")
    public KakaoAccountField kakaoAccount;
}
