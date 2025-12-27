package com.example.demo.controller;

import com.example.demo.entity.Role;
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

        String email = "test@gmail.com";
        String role = Role.ROLE_USER.name();

        return jwtTokenProvider.generateToken(email, role);
    }
}
