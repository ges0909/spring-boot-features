package com.valantic;

import java.lang.reflect.Field;
import java.util.Optional;


public class DataClassUtils {

    static public <T> Optional<T> getFieldValue(Object instance, String propertyPath, Class<T> propertyType) {
        String[] fieldNames = propertyPath.split("\\.");
        for (String fieldName : fieldNames) {
            try {
                Field field = instance.getClass().getDeclaredField(fieldName);
                field.setAccessible(true);
                instance = field.get(instance);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                return Optional.empty();
            }
        }
        return (propertyType.isInstance(instance))
                ? Optional.of(propertyType.cast(instance))
                : Optional.empty();
    }
}
