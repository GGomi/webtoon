package com.essri.webtoon.web.data;

import lombok.AllArgsConstructor;
import lombok.Setter;


@Setter
@AllArgsConstructor
public class ToonInfo {
    private String provider;
    private String day;
    private String toonName;
    private String toonHref;
    private String toonImgsrc;


}

