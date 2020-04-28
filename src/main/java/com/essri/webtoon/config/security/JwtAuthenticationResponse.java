package com.essri.webtoon.config.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthenticationResponse implements Serializable {
    private String accessToken;
    private String tokenType = "Bearer";
}
