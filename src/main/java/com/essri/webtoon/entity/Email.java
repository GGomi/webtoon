package com.essri.webtoon.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonIgnoreProperties({"host","id"})
public class Email {

    @javax.validation.constraints.Email
    @Column(name = "email",nullable = false, unique = true)
    private String value;

    @Builder
    public Email(String value) {
        this.value = value;
    }

    public String getHost() {
        int index = value.indexOf("@");
        return value.substring(index);
    }

    public String getId() {
        int index = value.indexOf("@");
        return value.substring(0, index);
    }
}
