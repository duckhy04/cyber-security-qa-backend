package org.example.cybersecurityqabackend.service.auth;

import org.example.cybersecurityqabackend.dto.LoginRequest;
import org.example.cybersecurityqabackend.dto.RegisterRequest;
import org.example.cybersecurityqabackend.dto.RegisterResponse;

public interface AuthService {
    String login(LoginRequest loginDto);
    RegisterResponse register(RegisterRequest registerDto);
}
