package com.valantic.sti.apps;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.*;

import com.valantic.sti.MainApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@TestPropertySource("classpath:application-test.yml")
public class TestPropertySourceAnnotationClasspathTest {

    @Autowired
    MainApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Heike", mainApplication.firstName);
    }
}
