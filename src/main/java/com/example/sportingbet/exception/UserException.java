package com.example.sportingbet.exception;


import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class UserException extends Exception {
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;

    public UserException(String string, HttpStatus httpStatus, ZonedDateTime timestamp) {
        super(string);
        this.httpStatus = httpStatus;
        this.timestamp = timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }
}

