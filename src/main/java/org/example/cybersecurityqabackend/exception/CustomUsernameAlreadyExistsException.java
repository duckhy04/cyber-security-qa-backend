package org.example.cybersecurityqabackend.exception;

public class CustomUsernameAlreadyExistsException extends RuntimeException {
    public CustomUsernameAlreadyExistsException(String message) {
        super(message);
    }
}