package com.valantic.sti.springbootdependsonbean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootDependsOnBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDependsOnBeanApplication.class, args);
    }

}
