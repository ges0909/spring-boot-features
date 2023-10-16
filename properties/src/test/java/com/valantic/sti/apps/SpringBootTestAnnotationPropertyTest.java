package com.valantic.sti.apps;

import org.junit.jupiter.api.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import com.valantic.sti.MainApplication;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "person.firstName=Friederike")
public class SpringBootTestAnnotationPropertyTest {

    @Autowired
    MainApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Friederike", mainApplication.firstName);
    }
}
