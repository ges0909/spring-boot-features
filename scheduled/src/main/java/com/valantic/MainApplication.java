package com.valantic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MainApplication {
    public static void main(final String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
    }
}
