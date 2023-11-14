package com.valantic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {HelloController.class})
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void whenRequestHello_thenReturnHello() throws Exception {
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    void whenRequestHelloWithName_thenReturnHelloWithName() throws Exception {
        mvc.perform(get("/hello/Heike"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, Heike"));
    }

    @Test
    void whenRequestHelloWithName_thenReturnBadRequest() throws Exception {
        mvc.perform(get("/hello/throw"))
                .andExpect(status().isBadRequest());
    }
}
