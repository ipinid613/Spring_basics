package com.sparta.w1homework;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class W1HomeworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(W1HomeworkApplication.class, args);
    }

}
