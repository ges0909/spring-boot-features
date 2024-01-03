package com.valantic;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record Person(@NotBlank(message = "Darf nicht leer sein.") String firstname,
                     @NotBlank(message = "{validation.not_empty}") String lastname,
                     @NotNull Integer age) {
}