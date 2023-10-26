package com.valantic;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record KategorieDto(
        @NotBlank
        @Size(max = 4, message = "darf nicht mehr als {max} Zeichen haben")
        String id,

        @NotBlank
        @Size(max = 25, message = "darf nicht mehr als {max} Zeichen haben")
        String name,

        @NotNull
        Short prioritaet
) {
}
