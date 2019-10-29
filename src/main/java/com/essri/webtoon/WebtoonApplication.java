package com.essri.webtoon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class WebtoonApplication {

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:application.properties,"
            + "/app/config/webtoon/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebtoonApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }
}
