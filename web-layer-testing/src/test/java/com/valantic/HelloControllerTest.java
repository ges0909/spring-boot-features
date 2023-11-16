package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// start full Spring application context but without the server
// @SpringBootTest
// @AutoConfigureMockMvc(addFilters = false) // injects 'MockMvc'

// narrow the tests to only the web layer
@WebMvcTest(controllers = {HelloController.class}, excludeFilters = {})
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
}
