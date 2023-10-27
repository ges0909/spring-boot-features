package com.valantic;

import org.apache.commons.beanutils.PropertyUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnnotationTest {


    @Test
    public void testIt() throws Exception {
        KategorieDto dto = new KategorieDto("ABC", "Alphabet", (short) 1);
        var name = getNestedProperty(dto, "name", String.class);
        assertEquals("Alphabet", name);
    }

    private <T> T getNestedProperty(Object object, String property, Class<T> type)
            throws Exception {
        Object value = PropertyUtils.getNestedProperty(object, property);
        if (type.isInstance(value)) {
            return type.cast(value);
        }
        throw new Exception("Invalid type");
    }
}
