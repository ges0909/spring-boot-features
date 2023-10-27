package com.valantic;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataClassUtilsTest {

    Kategorie kategorie = new Kategorie("ABC", "alphabet");
    MeldungTemplate meldungTemplate = new MeldungTemplate(9961L, "Meldungstext", kategorie);

    @Test
    public void simpleAccessReturningString() {
        Optional<String> name = DataClassUtils.getFieldValue(kategorie, "name", String.class);
        assertTrue(name.isPresent());
        assertEquals("alphabet", name.get());
    }

    @Test
    public void simpleAccessReturningLong() {
        Optional<Long> nr = DataClassUtils.getFieldValue(meldungTemplate, "meldungNr", Long.class);
        assertTrue(nr.isPresent());
        assertEquals(9961L, nr.get());
    }

    @Test
    public void nestedAccessReturningString() {
        Optional<String> name = DataClassUtils.getFieldValue(meldungTemplate, "kategorie.name", String.class);
        assertTrue(name.isPresent());
        assertEquals("alphabet", name.get());
    }
}
