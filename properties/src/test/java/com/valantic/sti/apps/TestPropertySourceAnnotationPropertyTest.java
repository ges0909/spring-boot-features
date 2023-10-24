package com.valantic.sti.apps;

import com.valantic.sti.LDAPApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@TestPropertySource(properties = "person.firstName=Vinzenz")
public class TestPropertySourceAnnotationPropertyTest {

    @Autowired
    LDAPApplication mainApplication;

    @Test
    void testPropertyValue() {
        assertEquals("Vinzenz", mainApplication.firstName);
    }
}
