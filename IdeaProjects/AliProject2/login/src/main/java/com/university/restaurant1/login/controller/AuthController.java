package com.university.restaurant1.login.controller;
import com.university.restaurant1.login.dto.LoginDTO;
import com.university.restaurant1.login.dto.LoginResponse;
import com.university.restaurant1.login.dto.SignupDTO;
import com.university.restaurant1.login.dto.SignupResponse;
import com.university.restaurant1.login.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public SignupResponse signup(@RequestBody SignupDTO signupDTO) {
        return authService.registerUser(signupDTO);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginDTO loginDTO) {
        return authService.login(loginDTO);
    }
}



