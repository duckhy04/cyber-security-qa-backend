package org.example.cybersecurityqabackend.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private UserDto user;
//    private String tokenType = "Bearer";
}
