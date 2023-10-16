package com.valantic.sti.apps;

import org.junit.jupiter.api.*;

import org.springframework.boot.test.context.*;
import org.springframework.context.*;

import com.valantic.sti.MainApplication;

import static org.junit.jupiter.api.Assertions.*;

// @SpringBootTest
@SpringBootTest()
class MainApplicationIntegrationTest {

    // Using JUnit5, the ApplicationContext is injected automagically, but we could replace this with an @Autowired field, too.
    // https://www.jvt.me/posts/2021/06/25/spring-context-test/
    private final ApplicationContext applicationContext;

    private final MainApplication mainApplication;

    public MainApplicationIntegrationTest(ApplicationContext applicationContext, MainApplication mainApplication) {
        this.applicationContext = applicationContext;
        this.mainApplication = mainApplication;
    }

    @Test
    void contextLoads() {
        assertNotNull(applicationContext);
    }

    @Test
    void testPropertyValue() {
        assertEquals("Gerrit", mainApplication.firstName);
    }
}

