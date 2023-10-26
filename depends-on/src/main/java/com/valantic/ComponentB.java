package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@ConditionalOnProperty(name = "component.b", havingValue = "true")
public class ComponentB {
    private final Logger logger = LoggerFactory.getLogger(ComponentB.class);

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.MILLISECONDS)
    void doSomethingPeriodically() {
        logger.debug("thread {}", Thread.currentThread().getName());
    }
}
