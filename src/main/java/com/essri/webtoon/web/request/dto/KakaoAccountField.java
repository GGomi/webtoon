package com.essri.webtoon.web.request.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "profile_needs_agreement",
        "profile"
})
@ToString
public class KakaoAccountField {

    @JsonProperty("profile_needs_agreement")
    public Boolean profileNeedsAgreement;
    @JsonProperty("profile")
    public KakaoProfileField profile;

}
