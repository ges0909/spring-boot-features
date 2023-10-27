package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@TestPropertySource(properties = "person.firstName=Vinzenz")
class TestPropertySourceAnnotationPropertyTest {

    @Autowired
    MainApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Vinzenz", mainApplication.firstName);
    }
}
