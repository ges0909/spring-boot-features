package com.valantic.sti;


import org.springframework.retry.annotation.*;
import org.springframework.stereotype.*;

import org.slf4j.*;


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
