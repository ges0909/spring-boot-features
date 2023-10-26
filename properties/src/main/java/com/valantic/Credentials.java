package com.valantic;

import org.hibernate.validator.constraints.Length;

public record Credentials(
        @Length(max = 4, min = 1)
        String authMethod,

        String username,

        String password
) {
}
