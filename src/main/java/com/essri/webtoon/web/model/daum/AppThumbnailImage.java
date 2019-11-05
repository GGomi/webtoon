package com.essri.webtoon.web.model.daum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class AppThumbnailImage {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("url")
    public String url;
    @JsonProperty("name")
    public Object name;
    @JsonProperty("size")
    public Object size;
    @JsonProperty("width")
    public Integer width;
    @JsonProperty("height")
    public Integer height;
    @JsonProperty("mediaType")
    public Object mediaType;
    @JsonProperty("encryptKey")
    public Object encryptKey;
    @JsonProperty("serviceStatus")
    public String serviceStatus;
}
