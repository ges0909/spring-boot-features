package com.valantic;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "ldap")
public record LdapConfig(
        @NotBlank String url,
        @NotBlank String base,
        @NotBlank String username,
        @NotBlank String password) {
}
