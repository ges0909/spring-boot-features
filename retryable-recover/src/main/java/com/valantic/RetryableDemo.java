package com.valantic;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;


@Component
public class RetryableDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryableDemo.class);

    @Retryable
    public void defaultRetryable() {
        LOGGER.info("defaultRetryable");
        throw new RuntimeException("Retryable");
    }

    @Retryable(retryFor = RuntimeException.class)
    public void retryableRetryFor() {
        LOGGER.info("retryableRetryFor");
        throw new RuntimeException("Retryable");
    }

    @Retryable(maxAttempts = 5)
    public void retryableWithMaxAttempts() {
        LOGGER.info("retryableWithMaxAttempts");
        throw new RuntimeException("Retryable");
    }

}
