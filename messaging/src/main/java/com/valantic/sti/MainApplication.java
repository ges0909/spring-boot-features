package com.valantic.sti;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.builder.*;
import org.springframework.context.*;

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
