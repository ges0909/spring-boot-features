package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Component
// @ConditionalOnExpression("${component.a:true} and ${component.b:true}")
@ConditionalOnProperty(name = {"component.a", "component.b"}, havingValue = "true")
// @DependsOn("componentB")
public class ComponentA {
    private final Logger logger = LoggerFactory.getLogger(ComponentA.class);

    private final ComponentB componentB;
    private final AtomicInteger atomicCounter = new AtomicInteger(0);
    private int simpleCounterValue = 0;

    @Autowired
    public ComponentA(ComponentB componentB) {
        this.componentB = componentB;
    }

    @Scheduled(fixedRate = 100, timeUnit = TimeUnit.MILLISECONDS)
    void doSomethingPeriodically() {
        simpleCounterValue++;
        int atomicCounterValue = atomicCounter.incrementAndGet();
        logger.info("thread {}, simple {}, atomic {}", Thread.currentThread().getName(), simpleCounterValue, atomicCounterValue);
        // Assert.isTrue(true, "Something went wrong");
        if (simpleCounterValue != atomicCounterValue) {
            System.exit(9961);
        }
        if (atomicCounterValue == 100) {
            simpleCounterValue = 0;
            atomicCounter.set(0);
        }
    }
}
