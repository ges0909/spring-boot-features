package com.valantic.sti.apps;

import com.valantic.sti.LDAPApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@TestPropertySource("classpath:application-test.yml")
public class TestPropertySourceAnnotationClasspathTest {

    @Autowired
    LDAPApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Heike", mainApplication.firstName);
    }
}
