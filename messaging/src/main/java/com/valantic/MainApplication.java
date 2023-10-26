package com.valantic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class MainApplication {

    private final ApplicationContext applicationContext;

    @Autowired
    public MainApplication(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(MainApplication.class)
                // .web(WebApplicationType.NONE)
                .run(args);
    }
}
