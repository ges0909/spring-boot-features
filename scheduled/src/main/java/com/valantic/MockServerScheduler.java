package com.valantic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = "mockserver.enabled", matchIfMissing = true)
public class MockServerScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockServerScheduler.class);

    private final LogMessageRepository logMessageRepository;

    public MockServerScheduler(final LogMessageRepository logMessageRepository) {
        this.logMessageRepository = logMessageRepository;
    }

    @Scheduled(fixedRate = 1000L)
    void logToFile() {
        LOGGER.info("mock server");
    }

    // @Scheduled(fixedRate = 5L, timeUnit = TimeUnit.SECONDS)
    // @Scheduled(fixedRateString = "PT5S")
    // @Scheduled(cron = "*/5 * * * * MON-FRI")
    @Scheduled(fixedRateString = "${mockserver.rate.database}")
    void logToDatabase() {
        final var logMessage = new LogMessage();
        logMessage.setText("scheduled");
        logMessageRepository.save(logMessage);
        LOGGER.info("scheduled: count={}", logMessageRepository.count());
    }
}
