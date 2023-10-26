package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(properties = "person.firstName=Friederike")
public class SpringBootTestAnnotationPropertyTest {

    @Autowired
    MainApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Friederike", mainApplication.firstName);
    }
}
