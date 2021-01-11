package com.example.sportingbet.service;

import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.exception.UserException;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface BetValidationService {
    void setEventScore(String location) throws EventException, IOException;

    ResponseEntity<Object> winBet(Long id) throws UserException, EventException;
}
