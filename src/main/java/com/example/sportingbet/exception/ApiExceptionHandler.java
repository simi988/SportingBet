package com.example.sportingbet.exception;

import com.example.sportingbet.model.ApiExceptionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Object> responseEntity(DuplicateUsernameException duplicateUsernameException) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionModel z = new ApiExceptionModel(duplicateUsernameException.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z")));
        return new ResponseEntity<>(z, badRequest);
    }

}
