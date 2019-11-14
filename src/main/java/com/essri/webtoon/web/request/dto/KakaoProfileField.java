package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonPropertyOrder({
        "nickname",
        "thumbnail_image_url",
        "profile_image_url"
})
public class KakaoProfileField {

    @JsonProperty("nickname")
    private String nickName;

    @JsonProperty("thumbnail_image_url")
    private String thumbnailImageUrl;

    @JsonProperty("profile_image_url")
    private String profileImageUrl;
}
