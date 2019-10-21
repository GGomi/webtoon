package com.essri.webtoon.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class WebtoonService {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("webtoonId")
    public Integer webtoonId;

    @JsonProperty("serviceTarget")
    public String serviceTarget;



}
