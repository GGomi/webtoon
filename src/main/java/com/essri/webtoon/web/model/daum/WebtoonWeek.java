package com.essri.webtoon.web.model.daum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WebtoonWeek {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("weekDay")
    public String weekDay;

}
