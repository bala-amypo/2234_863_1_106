package com.example.demo.controller;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public String login() {
        return jwtTokenProvider.generateToken("test@gmail.com");
    }

    @PostMapping("/register")
    public String register() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("123456");
        user.setRole(Role.ROLE_USER); // ✅ CORRECT

        return "User registered";
    }
}
