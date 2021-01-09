package com.example.sportingbet.api;

import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.ApiResponse;
import com.example.sportingbet.service.ValidationService;
import com.example.sportingbet.service.ValidationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/v1/validation")
@RestController
public class ValidationController {
    ValidationService validationService;

    @Autowired
    public ValidationController(ValidationService validationService) {
        this.validationService = validationService;
    }

    @PostMapping
    public ResponseEntity<Object> setEventScore() throws IOException, EventException {
        validationService.setEventScore();
        return new ResponseEntity<>("Event score is added", HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> winBet(@PathVariable Long id) throws UserException, EventException {
        return  validationService.winBet(id);
    }


}
