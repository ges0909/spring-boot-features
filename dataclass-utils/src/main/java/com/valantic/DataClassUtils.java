package com.valantic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;


public class DataClassUtils {

    private static final String FIELD_DELIMITER_REGEX = "\\.";

    private DataClassUtils() {
    }

    public static <T> Optional<T> getNestedFieldValue(Object instance, String fieldPath, Class<T> valueType) {
        String[] fieldNames = fieldPath.split(FIELD_DELIMITER_REGEX);
        for (String fieldName : fieldNames) {
            try {
                Method method = instance.getClass().getMethod(fieldName);
                instance = method.invoke(instance);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                return Optional.empty();
            }
        }
        return (valueType.isInstance(instance))
                ? Optional.of(valueType.cast(instance))
                : Optional.empty();
    }
}
