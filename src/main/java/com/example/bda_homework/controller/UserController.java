package com.example.bda_homework.controller;

import com.example.bda_homework.model.User;
import com.example.bda_homework.model.UserVO;
import com.example.bda_homework.service.UserService;
import com.example.bda_homework.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
            User user = userService.authenticate(username, password);
            String role = user.getRole();
            String token = jwtUtil.generateToken(username, role);
            return ResponseEntity.ok(token);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body("Invalid username or password");
        }
    }

    @PostMapping("/vip")
    public ResponseEntity<?> upgradeToVip(@RequestParam String userName) {
        userService.upgradeToVip(userName);
        return ResponseEntity.ok("User upgraded to VIP successfully");
    }
}