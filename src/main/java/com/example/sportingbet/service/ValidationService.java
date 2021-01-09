package com.example.sportingbet.service;

import com.example.sportingbet.entity.EventDO;
import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ValidationService {
    void setEventScore() throws EventException, IOException;
    ResponseEntity<Object> winBet(Long id) throws UserException, EventException;
}
