package com.essri.webtoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WebtoonApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebtoonApplication.class, args);
    }
}
