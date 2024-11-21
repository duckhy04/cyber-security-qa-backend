package org.example.cybersecurityqabackend.exception;

import org.example.cybersecurityqabackend.entity.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler(CustomResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(CustomResourceNotFoundException ex) {
        Error error = new Error("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(CustomUsernameAlreadyExistsException.class)
    public ResponseEntity<Error> handleUsernameAlreadyExistsException(CustomUsernameAlreadyExistsException ex) {
        Error error = new Error("USERNAME_ALREADY_EXISTS", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CustomBadRequestException.class)
    public ResponseEntity<Error> handleBadRequestException(CustomBadRequestException ex) {
        Error error = new Error("BAD_REQUEST", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(CustomInternalServerErrorException.class)
    public ResponseEntity<Error> handleInternalServerErrorException(CustomInternalServerErrorException ex) {
        Error error = new Error("INTERNAL_SERVER_ERROR", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(CustomUnauthorizedException.class)
    public ResponseEntity<Error> handleUnauthorizedException(CustomUnauthorizedException ex) {
        Error error = new Error("UNAUTHORIZED", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(CustomForbiddenException.class)
    public ResponseEntity<Error> handleForbiddenException(CustomForbiddenException ex) {
        Error error = new Error("FORBIDDEN", ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    @ExceptionHandler(CustomConflictException.class)
    public ResponseEntity<Error> handleConflictException(CustomConflictException ex) {
        Error error = new Error("CONFLICT", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(CustomMethodNotAllowedException.class)
    public ResponseEntity<Error> handleMethodNotAllowedException(CustomMethodNotAllowedException ex) {
        Error error = new Error("METHOD_NOT_ALLOWED", ex.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
    }

    @ExceptionHandler(CustomNotAcceptableException.class)
    public ResponseEntity<Error> handleNotAcceptableException(CustomNotAcceptableException ex) {
        Error error = new Error("NOT_ACCEPTABLE", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(error);
    }
}
