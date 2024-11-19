package org.example.cybersecurityqabackend.exception;

import org.example.cybersecurityqabackend.entity.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException ex) {
        Error error = new Error("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<Error> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex) {
        Error error = new Error("USERNAME_ALREADY_EXISTS", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
