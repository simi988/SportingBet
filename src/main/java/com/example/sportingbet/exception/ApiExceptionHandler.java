package com.example.sportingbet.exception;

import com.example.sportingbet.model.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Object> handleApiRequestException(UserException userException) {
        HttpStatus status = userException.getHttpStatus();
        ApiException apiException = new ApiException(userException.getMessage(),
                status);
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(EventException.class)
    public ResponseEntity<Object> handleApiRequestException(EventException eventException) {
        HttpStatus status = eventException.getHttpStatus();
        ApiException apiException = new ApiException(eventException.getMessage(),
                status);
        return new ResponseEntity<>(apiException, status);
    }

    @ExceptionHandler(BetException.class)
    public ResponseEntity<Object> handleApiRequestException(BetException betException) {
        HttpStatus status = betException.getHttpStatus();
        ApiException apiException = new ApiException(betException.getMessage(),
                status);
        return new ResponseEntity<>(apiException, status);
    }

}
