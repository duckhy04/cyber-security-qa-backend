package org.example.cybersecurityqabackend.controller;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.*;
import org.example.cybersecurityqabackend.entity.User;
import org.example.cybersecurityqabackend.service.auth.AuthService;
import org.example.cybersecurityqabackend.service.user.UserSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserSerivce userSerivce;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginDto){
        String token = authService.login(loginDto);

        User user = userSerivce.findByUsername(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setName(user.getName());

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        loginResponse.setUser(userDto);

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerDto){
        RegisterResponse registerResponse = authService.register(registerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registerResponse);
    }
}

