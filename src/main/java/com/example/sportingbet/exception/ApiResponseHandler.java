package com.example.sportingbet.exception;

import com.example.sportingbet.model.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiResponseHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleApiRequestResponse(UserException userException) {
        HttpStatus badRequest = userException.getHttpStatus();
        ApiException apiException = new ApiException(userException.getMessage(),
                badRequest,
                userException.getTimestamp());
        return new ResponseEntity<>(apiException, badRequest);
    }
}
