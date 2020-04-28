package com.essri.webtoon.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {
    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");
    private String value;
}
