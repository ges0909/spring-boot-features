package com.valanticsti.condition;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

record AnlagenStatus(String kuerzel, boolean wirkbetrieb, String rolle) {
}

public class MapToStringTest {

    private static final Logger LOG = LoggerFactory.getLogger(MapToStringTest.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setup() {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    void mapToString() {
        Map<String, AnlagenStatus> anlagenStatusMap = Map.of(
                "a-z1", new AnlagenStatus("a-z1", false, "ACTIVE_N"),
                "a-z2", new AnlagenStatus("a-z2", false, "ACTIVE_N"),
                "a-z3", new AnlagenStatus("a-z3", false, "ACTIVE_N"),
                "a-z4", new AnlagenStatus("a-z4", false, "ACTIVE_N")
        );
        LOG.info("{}", mapToString(anlagenStatusMap));
    }

    private <K, V> String mapToString(Map<K, V> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            LOG.error("JSON-Fehler beim Verarbeiten der Antwort vom Environment-Controller JSON: {}", e.getMessage());
        }
        return "";
    }
}

