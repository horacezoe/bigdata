package com.example.bda_homework;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PythonScriptServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPythonBackendUrl() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("http://127.0.0.1:50000/similar_papers"))
                .andExpect(status().isOk());
    }
}
