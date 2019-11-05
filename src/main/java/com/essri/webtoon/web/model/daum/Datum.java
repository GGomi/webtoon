package com.essri.webtoon.web.model.daum;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Datum {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("nickname")
    public String nickname;

    @JsonProperty("webtoonType")
    public String webtoonType;

    @JsonProperty("title")
    public String title;

    @JsonProperty("englishTitle")
    public Object englishTitle;

    @JsonProperty("finishYn")
    public String finishYn;

    @JsonProperty("titleImage2")
    public Object titleImage2;

    @JsonProperty("thumbnailImage")
    public ThumbnailImage thumbnailImage;

    @JsonProperty("thumbnailImage2")
    public ThumbnailImage thumbnailImage2;

    @JsonProperty("padThumbnailImage")
    public Object padThumbnailImage;

    @JsonProperty("artistCommentImage")
    public Object artistCommentImage;

    @JsonProperty("homeThumbnailImage")
    public Object homeThumbnailImage;

    @JsonProperty("appThumbnailImage")
    public AppThumbnailImage appThumbnailImage;

    @JsonProperty("introduction")
    public String introduction;

    @JsonProperty("serviceStatus")
    public String serviceStatus;

    @JsonProperty("weekTerm")
    public String weekTerm;

    @JsonProperty("articleId")
    public Integer articleId;

    @JsonProperty("media")
    public String media;

    @JsonProperty("url")
    public Object url;

    @JsonProperty("simpleUrl")
    public Object simpleUrl;

    @JsonProperty("webtoonGroupId")
    public Object webtoonGroupId;

    @JsonProperty("payYn")
    public String payYn;

    @JsonProperty("payType")
    public String payType;

    @JsonProperty("price")
    public Integer price;

    @JsonProperty("ageGrade")
    public Integer ageGrade;

    @JsonProperty("restYn")
    public String restYn;

    @JsonProperty("monopolize")
    public String monopolize;

    @JsonProperty("dateCreated")
    public String dateCreated;

    @JsonProperty("webtoonComment")
    public Object webtoonComment;

    @JsonProperty("cp")
    public Object cp;

    @JsonProperty("webtoonWeeks")
    public List<WebtoonWeek> webtoonWeeks = null;

    @JsonProperty("webtoonEpisodes")
    public Object webtoonEpisodes;

    @JsonProperty("previewWebtoonEpisodes")
    public Object previewWebtoonEpisodes;

    @JsonProperty("latestWebtoonEpisode")
    public LatestWebtoonEpisode latestWebtoonEpisode;

    @JsonProperty("webtoonServices")
    public List<WebtoonService> webtoonServices = null;

    @JsonProperty("relatedProducts")
    public Object relatedProducts;

    @JsonProperty("promotionContents")
    public Object promotionContents;

    @JsonProperty("score")
    public Double score;

    @JsonProperty("tag")
    public Object tag;

    @JsonProperty("isNew")
    public Boolean isNew;

    @JsonProperty("averageScore")
    public Double averageScore;

    @JsonProperty("seriesYn")
    public Object seriesYn;

    @JsonProperty("ranking")
    public Integer ranking;

    @JsonProperty("diff")
    public Integer diff;

    @JsonProperty("metricsScore")
    public Integer metricsScore;

    @JsonProperty("sort")
    public String sort;

    @JsonProperty("sortWeight")
    public Integer sortWeight;


}
