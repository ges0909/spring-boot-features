package com.valantic.sti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@EnableRetry
public class MainApplication {

    public static void main(final String[] args) {
        final ConfigurableApplicationContext context = SpringApplication.run(MainApplication.class, args);
        // RetryDemo retryDemo = context.getBean(RetryDemo.class);
        // retryDemo.callRetryableMethod();
    }

}
