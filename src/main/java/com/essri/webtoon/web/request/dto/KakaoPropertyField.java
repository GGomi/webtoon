package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "nickname",
        "profile_image",
        "thumbnail_image"
})
@ToString
public class KakaoPropertyField {

    @JsonProperty("nickname")
    public String nickname;
    @JsonProperty("profile_image")
    public String profileImage;
    @JsonProperty("thumbnail_image")
    public String thumbnailImage;

}
