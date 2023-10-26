package com.valantic;

import com.valantic.sti.LDAPApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "person.firstName=Friederike")
public class SpringBootTestAnnotationPropertyTest {

    @Autowired
    LDAPApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Friederike", mainApplication.firstName);
    }
}
