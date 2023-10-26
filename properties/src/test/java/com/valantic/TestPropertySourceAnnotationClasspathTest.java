package com.valantic;

import com.valantic.sti.LDAPApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

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
