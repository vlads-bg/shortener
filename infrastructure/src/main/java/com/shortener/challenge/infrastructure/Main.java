package com.shortener.challenge.infrastructure;

import com.shortener.challenge.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main (String[] args) {
        System.out.println("test");
        SpringApplication.run(WebServerConfig.class, args);
    }
}
