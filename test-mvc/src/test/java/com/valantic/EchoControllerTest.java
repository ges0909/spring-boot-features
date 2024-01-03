package com.valantic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 1. starts full Spring application context but without the server

// @SpringBootTest(classes = {HelloController.class})
// @AutoConfigureMockMvc(addFilters = false) // injects 'MockMvc'
// @EnableWebMvc

// 2. narrows the tests to only the web layer
@WebMvcTest(controllers = {EchoController.class})
@ExtendWith(OutputCaptureExtension.class)
class EchoControllerTest {

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
    void whenValidationSucceedsShouldReturnOk() throws Exception {
        final String credentials = """
                {
                    "firstname": "gerrit",
                    "lastname": "secret",
                    "age": 25
                }
                """;
        mockMvc.perform(post("/echo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(credentials))
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
    void whenValidationFailsShouldReturnBadRequest(String credentials, CapturedOutput output) throws Exception {
        mockMvc.perform(post("/echo")
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(credentials))
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
