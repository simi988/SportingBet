package com.example.sportingbet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorResponse {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<String> responseEntity(DuplicateUsernameException duplicateUsernameException) {
        String message = createMessage(duplicateUsernameException, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    private String createMessage(DuplicateUsernameException duplicateUsernameException, HttpStatus httpStatus) {
        String message = duplicateUsernameException.getMessage();
        int value = httpStatus.value();
        LocalDateTime localDate = LocalDateTime.now();
        return "Status: " + value + "\r\n" + "Message: " + message + "\r\n" + "Timestamp: " + localDate;
    }
}
