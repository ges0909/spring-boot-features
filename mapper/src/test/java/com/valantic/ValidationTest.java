package com.valantic;

import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static jakarta.validation.Validation.buildDefaultValidatorFactory;

public class ValidationTest {

    Validator validator;

    @BeforeEach
    void setup() {
        try (ValidatorFactory factory = buildDefaultValidatorFactory()) {
            validator = factory.getValidator();
        }
    }

    @Test
    void testMethodValidation() {
        TestClass sut = new TestClass();
        sut.testMethod(null);
        sut.testMethod("");
    }
}

class TestClass {
    public void testMethod(@NotBlank final String param) {
    }
}
