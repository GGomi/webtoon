package com.essri.webtoon.toon.model.daum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class DaumRestTemplate {

    @JsonProperty("result")
    public Result result;

    @JsonProperty("data")
    public List<Datum> data;

}
