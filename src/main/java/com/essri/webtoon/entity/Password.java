package com.essri.webtoon.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "passwd", nullable = false)
    private String value;

    @Builder
    public Password(final String value) {
        this.value = encodePassword(value);
    }

    private String encodePassword(final String passwd) {
        return new BCryptPasswordEncoder().encode(passwd);
    }
}
