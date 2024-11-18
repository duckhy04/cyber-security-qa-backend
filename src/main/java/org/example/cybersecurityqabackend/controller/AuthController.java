package org.example.cybersecurityqabackend.controller;

import lombok.AllArgsConstructor;
import org.example.cybersecurityqabackend.dto.JwtAuthResponse;
import org.example.cybersecurityqabackend.dto.LoginDto;
import org.example.cybersecurityqabackend.dto.RegisterDto;
import org.example.cybersecurityqabackend.dto.UserDto;
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
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        String token = authService.login(loginDto);

        // Lấy thông tin người dùng
        User user = userSerivce.findByUsername(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail());
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        jwtAuthResponse.setUser(userDto);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerDto));
    }
}

