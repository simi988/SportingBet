package com.example.sportingbet.exception;

import com.example.sportingbet.model.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<Object> handleApiRequestException(DuplicateUsernameException duplicateUsernameException) {
        HttpStatus badRequest = duplicateUsernameException.getHttpStatus();
        ApiException apiException = new ApiException(duplicateUsernameException.getMessage(),
                badRequest,
                duplicateUsernameException.getTimestamp());
        return new ResponseEntity<>(apiException, badRequest);
    }

}
