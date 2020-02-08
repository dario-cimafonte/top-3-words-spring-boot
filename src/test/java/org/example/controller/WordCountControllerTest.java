package org.example.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
public class WordCountControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnTop3Words() throws Exception {
        mockMvc.perform(post("/top-3-words").contentType(MediaType.APPLICATION_OCTET_STREAM).content("c a b a b a"))
                .andExpect(content().json("[\"a\", \"b\", \"c\"]"));
    }
}