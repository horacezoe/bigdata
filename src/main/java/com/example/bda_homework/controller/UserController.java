package com.example.bda_homework.controller;

import com.example.bda_homework.model.UserVO;
import com.example.bda_homework.service.UserService;
import com.example.bda_homework.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserVO userVO) {
        userService.registerUser(userVO);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }

        String role = userService.getUserRole(username);
        String token = jwtUtil.generateToken(username, role);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/vip")
    public ResponseEntity<?> upgradeToVip(@RequestParam Long userId) {
        userService.upgradeToVip(userId);
        return ResponseEntity.ok("User upgraded to VIP successfully");
    }
}