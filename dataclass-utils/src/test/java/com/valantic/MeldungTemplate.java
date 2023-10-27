package com.valantic;


public record MeldungTemplate(
        Long meldungNr,
        String text,
        Kategorie kategorie
) {
}
