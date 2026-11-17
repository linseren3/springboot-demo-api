package com.example.demo;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void helloReturnsGreeting() throws Exception {
        mockMvc.perform(get("/api/hello").param("name", "GitHub"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, GitHub!"));
    }

    @Test
    void statusIsUp() throws Exception {
        mockMvc.perform(get("/api/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.service").value("springboot-demo-api"));
    }

    @Test
    void echoReturnsPayload() throws Exception {
        mockMvc.perform(post("/api/echo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"message\":\"ping\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.echo.message").value("ping"))
                .andExpect(jsonPath("$.fieldCount").value(1));
    }
}
