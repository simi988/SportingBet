package com.example.sportingbet.exception;


import org.springframework.http.HttpStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;

public class UserException extends Exception {

    private final HttpStatus httpStatus;
    private ZonedDateTime timestamp;

    public UserException(String string, HttpStatus httpStatus) {
        super(string);
        this.httpStatus = httpStatus;
        timestamp= ZonedDateTime.now(ZoneId.of("Z"));
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }


}

