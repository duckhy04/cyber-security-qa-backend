package org.example.cybersecurityqabackend.service.auth;

import org.example.cybersecurityqabackend.dto.LoginDto;
import org.example.cybersecurityqabackend.dto.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
