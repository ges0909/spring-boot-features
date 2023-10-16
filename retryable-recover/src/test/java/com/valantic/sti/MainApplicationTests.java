package com.valantic.sti;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.stream.Stream;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
class MainApplicationTests {

    @Autowired
    private RetryableDemo retryableDemo;

    @Test
    void defaultRetryable(CapturedOutput output) {
        Assertions.assertThrows(RuntimeException.class,
                () -> retryableDemo.defaultRetryable()
        );
        Assertions.assertEquals(3, count(output, "defaultRetryable"));
    }

    @Test
    void retryableWithRetryFor(CapturedOutput output)  {
        Assertions.assertThrows(RuntimeException.class,
                () -> retryableDemo.retryableRetryFor()
        );
        Assertions.assertEquals(3, count(output, "retryableRetryFor"));
    }

    @Test
    void retryableWithMaxAttempts(CapturedOutput output)  {
        Assertions.assertThrows(RuntimeException.class,
                () -> retryableDemo.retryableWithMaxAttempts()
        );
        Assertions.assertEquals(5, count(output, "retryableWithMaxAttempts"));
    }

    private long count(CapturedOutput output, String match) {
        return Stream.of(output.getOut().split("\n")).filter(line -> line.contains(match)).count();
    }
}
