package com.valantic.sti.schedule;

import com.valantic.sti.model.LogMessage;
import com.valantic.sti.model.LogMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@ConditionalOnProperty(name = "mockserver.enabled", matchIfMissing = true)
public class MockServerScheduler {

    private final LogMessageRepository logMessageRepository;

    @Autowired
    public MockServerScheduler(final LogMessageRepository logMessageRepository) {
        this.logMessageRepository = logMessageRepository;
    }

    @Scheduled(fixedRate = 1000L)
    void logToFile() {
        log.info("mock server");
    }

    // @Scheduled(fixedRate = 5L, timeUnit = TimeUnit.SECONDS)
    // @Scheduled(fixedRateString = "PT5S")
    // @Scheduled(cron = "*/5 * * * * MON-FRI")
    @Scheduled(fixedRateString = "${mockserver.rate.database}")
    void logToDatabase() {
        final var logMessage = new LogMessage();
        logMessage.setText("scheduled");
        logMessageRepository.save(logMessage);
        log.info("scheduled: count={}", logMessageRepository.count());
    }
}
