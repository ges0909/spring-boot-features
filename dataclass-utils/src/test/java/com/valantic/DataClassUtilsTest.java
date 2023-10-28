package com.valantic;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DataClassUtilsTest {

    Kategorie kategorie = new Kategorie("ABC", "alphabet");
    MeldungTemplate meldungTemplate = new MeldungTemplate(9961L, "Meldungstext", kategorie);

    @Test
    void simpleAccessReturningString() {
        Optional<String> name = DataClassUtils.getNestedFieldValue(kategorie, "name", String.class);
        assertTrue(name.isPresent());
        assertEquals("alphabet", name.get());
    }

    @Test
    void simpleAccessReturningLong() {
        Optional<Long> nr = DataClassUtils.getNestedFieldValue(meldungTemplate, "meldungNr", Long.class);
        assertTrue(nr.isPresent());
        assertEquals(9961L, nr.get());
    }

    @Test
    void nestedAccessReturningString() {
        Optional<String> name = DataClassUtils.getNestedFieldValue(meldungTemplate, "kategorie.name", String.class);
        assertTrue(name.isPresent());
        assertEquals("alphabet", name.get());
    }
}
