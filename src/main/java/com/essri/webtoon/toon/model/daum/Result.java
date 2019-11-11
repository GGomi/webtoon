package com.essri.webtoon.toon.model.daum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Result {

    @JsonProperty("status")
    public String status;

    @JsonProperty("message")
    public String message;

}
