package com.example.sportingbet.api;

import com.example.sportingbet.exception.EventException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.service.BetValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RequestMapping("api/v1/validation")
@RestController
public class BetValidationController {
    BetValidationService betValidationService;

    @Autowired
    public BetValidationController(BetValidationService betValidationService) {
        this.betValidationService = betValidationService;
    }

    @PostMapping(path = "{location}")
    public ResponseEntity<Object> setEventScore(@PathVariable String location) throws IOException, EventException {
        betValidationService.setEventScore(location);
        return new ResponseEntity<>("Event score is added", HttpStatus.OK);
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> winBet(@PathVariable Long id) throws UserException, EventException {
        return betValidationService.winBet(id);
    }


}
