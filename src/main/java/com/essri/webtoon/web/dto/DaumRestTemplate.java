package com.essri.webtoon.web.dto;

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
