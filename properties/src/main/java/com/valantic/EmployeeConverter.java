package com.valantic;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class EmployeeConverter implements Converter<String, Employee> {
    @Override
    public Employee convert(final String from) {
        final String[] properties = from.split(",");
        return new Employee(properties[0], Double.parseDouble(properties[1]));
    }
}
