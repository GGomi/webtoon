package com.essri.webtoon.web.model.daum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Artist {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("penName")
    public String penName;
    @JsonProperty("introduction")
    public String introduction;
    @JsonProperty("history")
    public Object history;
    @JsonProperty("career")
    public Object career;
    @JsonProperty("pictureImage")
    public PictureImage pictureImage;
    @JsonProperty("smallPictureImage")
    public Object smallPictureImage;
    @JsonProperty("appImage")
    public Object appImage;
    @JsonProperty("nameImage")
    public Object nameImage;
    @JsonProperty("birthDay")
    public Object birthDay;
    @JsonProperty("debutDay")
    public Object debutDay;
    @JsonProperty("email")
    public String email;
    @JsonProperty("daumEmail")
    public Object daumEmail;
    @JsonProperty("daumEmailDisplayYn")
    public Object daumEmailDisplayYn;
    @JsonProperty("homepage")
    public String homepage;
    @JsonProperty("blog")
    public Object blog;
    @JsonProperty("cafe")
    public Object cafe;
    @JsonProperty("fancafe")
    public Object fancafe;
    @JsonProperty("facebook")
    public Object facebook;
    @JsonProperty("twitter")
    public Object twitter;
    @JsonProperty("teamYn")
    public Object teamYn;
    @JsonProperty("team")
    public Object team;
    @JsonProperty("joinDay")
    public Object joinDay;
    @JsonProperty("breakDay")
    public Object breakDay;
    @JsonProperty("artistOrder")
    public Integer artistOrder;
    @JsonProperty("artistType")
    public String artistType;
    @JsonProperty("authUserinfo")
    public Object authUserinfo;
    @JsonProperty("agency")
    public Object agency;
}
