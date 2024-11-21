package org.example.cybersecurityqabackend.exception;

public class CustomMethodNotAllowedException extends RuntimeException {
    public CustomMethodNotAllowedException(String message) {
        super(message);
    }
}
