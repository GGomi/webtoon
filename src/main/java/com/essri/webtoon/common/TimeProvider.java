package com.essri.webtoon.common;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {
    private static final long serialVersionUID = -3301695478208950415L;

    public Date now() {
        return new Date();
    }
}
