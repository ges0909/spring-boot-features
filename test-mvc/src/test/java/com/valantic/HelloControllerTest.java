package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// 1. starts full Spring application context but without the server

// @SpringBootTest(classes = {HelloController.class})
// @AutoConfigureMockMvc(addFilters = false) // injects 'MockMvc'
// @EnableWebMvc

// 2. narrows the tests to only the web layer
@WebMvcTest(controllers = {HelloController.class})
class HelloControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnHello() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    void shouldReturnHelloWithName() throws Exception {
        mockMvc.perform(get("/hello/Heike"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Heike"));
    }

    @Test
    void testChangeInitialPassword() throws Exception {
        mockMvc.perform(put("/isbpn-client-v1/bediener-verwaltung/initialpasswort-vergeben/{id}", 123L)
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
