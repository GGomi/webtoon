package com.essri.webtoon.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class LatestWebtoonEpisode {
    @JsonProperty("id")
    public Integer id;
    @JsonProperty("episode")
    public Integer episode;
    @JsonProperty("title")
    public String title;
    @JsonProperty("shortTitle")
    public Object shortTitle;
    @JsonProperty("thumbnailImage")
    public ThumbnailImage thumbnailImage;
    @JsonProperty("episodeImage")
    public EpisodeImage episodeImage;
    @JsonProperty("encryptUseYn")
    public String encryptUseYn;
    @JsonProperty("serviceStatus")
    public String serviceStatus;
    @JsonProperty("articleId")
    public Integer articleId;
    @JsonProperty("commentUseYn")
    public Object commentUseYn;
    @JsonProperty("dateCreated")
    public String dateCreated;
    @JsonProperty("webtoon")
    public Object webtoon;
    @JsonProperty("serviceType")
    public Object serviceType;
    @JsonProperty("multiType")
    public Object multiType;
    @JsonProperty("multiBgm")
    public Object multiBgm;
    @JsonProperty("multiBackgroundImage")
    public Object multiBackgroundImage;
    @JsonProperty("price")
    public Integer price;
    @JsonProperty("padtoonImage")
    public Object padtoonImage;
    @JsonProperty("voteTarget")
    public Object voteTarget;
    @JsonProperty("shareVoteTarget")
    public Object shareVoteTarget;
    @JsonProperty("isTopRecommend")
    public Boolean isTopRecommend;
    @JsonProperty("simpleUrl")
    public Object simpleUrl;
    @JsonProperty("specialSearchString")
    public Object specialSearchString;
    @JsonProperty("specialSearchUrl")
    public Object specialSearchUrl;
    @JsonProperty("previewEndDate")
    public Object previewEndDate;
    @JsonProperty("isPaid")
    public Boolean isPaid;
    @JsonProperty("payWebtoonEpisode")
    public Object payWebtoonEpisode;
    @JsonProperty("product")
    public Object product;
    @JsonProperty("ticketAvailable")
    public Boolean ticketAvailable;
    @JsonProperty("ageGrade")
    public Integer ageGrade;

}
