package com.valantic.sti;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;

@Validated
@ConfigurationProperties(prefix = "mail")
// @Configuration // variant 1
@ConfigurationPropertiesScan // variant 2
public record ConfigProperties(
        @NotBlank
        String hostName,

        @Min(1025)
        @Max(65536)
        int port,

        @Pattern(regexp = "^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,6}$")
        String from,

        List<String> defaultRecipients,

        Map<String, String> additionalHeaders,

        Credentials credentials
) {
}
