package com.valantic;

import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "mail.credentials")
public record Credentials(
        @Length(max = 4, min = 1)
        String authMethod,

        String username,

        String password
) {
}
