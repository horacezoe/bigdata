package com.example.bda_homework;

import com.example.bda_homework.model.UserVO;
import com.example.bda_homework.service.UserService;
import com.example.bda_homework.util.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Test
    public void testRegister() throws Exception {
        UserVO userVO = new UserVO(null, "testuser2", "testuser2@example.com", "ROLE_USER", "password123");
        ObjectMapper objectMapper = new ObjectMapper();
        String userJson = objectMapper.writeValueAsString(userVO);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testLogin() throws Exception {
        // First, register the user
//        UserVO userVO = new UserVO(null, "testuser4", "testuser4@example.com", "ROLE_USER", "password123");
//        userService.registerUser(userVO);

        // Then, attempt to login
        mockMvc.perform(MockMvcRequestBuilders.post("/api/user/login")
                .param("username", "testuser4")
                .param("password", "password123"))
                .andExpect(status().isOk());
    }
}
