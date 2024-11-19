package com.example.bda_homework.service;

import com.example.bda_homework.model.User;
import com.example.bda_homework.model.UserVO;
import com.example.bda_homework.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User authenticate(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null && passwordEncoder.matches(rawPassword, user.getPassword())) {
            return user;
        }
        throw new RuntimeException("Invalid username or password");
    }

    public void registerUser(UserVO userVO) {
        User user = new User();
        user.setUsername(userVO.getUsername());
        user.setEmail(passwordEncoder.encode(userVO.getEmail()));
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(userVO.getPassword()));
        userRepository.save(user);
    }

    public void upgradeToVip(String userName) {
        User user = userRepository.findByUsername(userName);
        user.setRole("ROLE_VIP");
        userRepository.save(user);
    }

    public String getUserInfo(String userName) {
        User user = userRepository.findByUsername(userName);
        return user.getRole();
    }
}