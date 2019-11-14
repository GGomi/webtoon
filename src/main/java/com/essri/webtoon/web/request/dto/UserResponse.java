package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserResponse {

    private Long id;

    @JsonProperty("is_joined")
    private String isJoined;

    @JsonProperty("nickname")
    private String nickname;
}
