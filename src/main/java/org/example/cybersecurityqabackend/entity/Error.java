package org.example.cybersecurityqabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Error {
    private String error;
    private String message;
}
