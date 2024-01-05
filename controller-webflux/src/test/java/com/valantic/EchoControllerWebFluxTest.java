package com.valantic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebFluxTest(controllers = {EchoControllerWebFlux.class})
class EchoControllerWebFluxTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnHello() throws Exception {
        mockMvc.perform(get("/echo"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    void shouldReturnHelloWithName() throws Exception {
        mockMvc.perform(get("/echo/Heike"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Heike"));
    }

    @Test
    void whenValidationSucceedsThenReturnOk() throws Exception {
        var person = """
                {
                    "firstname": "gerrit",
                    "lastname": "secret",
                    "age": 25
                }
                """;
        mockMvc.perform(post("/echo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(person))
                .andExpect(status().isOk());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            """
                    {
                         "firstname": "gerrit",
                         "lastname": "",
                         "age": 25
                     }
                     """,
            """
                    {
                        "firstname": "",
                        "lastname": ""
                    }
                    """,
            """
                    {
                        "firstname": "gerrit"
                    }
                    """
    })
    void whenValidationFailsThenReturnBadRequest(String person) throws Exception {
        mockMvc.perform(post("/echo")
                        // .header("Accept-Language", "de-DE")
                        .header("Accept-Language", "it-IT")
                        // .header("Accept-Language", "en-US")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(person))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testChangeInitialPassword() throws Exception {
        mockMvc.perform(put("/notfound/{id}", 123L)
                        .param("sessionId", "test")
                        .param("benutzerkennung", "test")
                        .param("bedienplatz", "test")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content("{\"password\": \"test\"}"))
                .andExpect(status().isNotFound());
    }
}
