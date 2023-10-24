package com.valantic.sti.apps;

import com.valantic.sti.LDAPApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// @SpringBootTest
@SpringBootTest()
class LDAPApplicationIntegrationTest {

    // Using JUnit5, the ApplicationContext is injected automagically, but we could replace this with an @Autowired field, too.
    // https://www.jvt.me/posts/2021/06/25/spring-context-test/
    private final ApplicationContext applicationContext;

    private final LDAPApplication mainApplication;

    public LDAPApplicationIntegrationTest(ApplicationContext applicationContext, LDAPApplication mainApplication) {
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

