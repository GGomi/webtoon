package com.essri.webtoon;

import com.essri.webtoon.batch.job.InsertWebtoonList;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
@RequiredArgsConstructor
public class WebtoonApplication {
    private final InsertWebtoonList insertWebtoonList;

    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml,"
            + "classpath:application.properties,"
            + "/home/ubuntu/app/config/springboot-webtoon/real-application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(WebtoonApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

    @PostConstruct
    public void setUp() {
        insertWebtoonList.insertWebtoonList();
    }

    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/v1/webtoon/**")
                        .allowedOrigins("*")
                        .allowedMethods(HttpMethod.POST.name(),HttpMethod.GET.name())
                        .allowCredentials(false)
                        .maxAge(3600);
            }
        };
    }

}
