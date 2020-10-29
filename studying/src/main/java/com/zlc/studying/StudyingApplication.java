package com.zlc.studying;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StudyingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyingApplication.class, args);
    }

}
