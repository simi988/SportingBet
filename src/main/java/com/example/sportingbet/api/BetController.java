package com.example.sportingbet.api;

import com.example.sportingbet.exception.BetException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.Bet;
import com.example.sportingbet.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/bet")
@RestController
public class BetController {
    BetService betService;

    @Autowired
    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping
    public ResponseEntity<Object> addBet(@Valid @NonNull @RequestBody Bet bet) throws BetException {
        betService.addBet(bet);
        return new ResponseEntity<>("Bet is added", HttpStatus.CREATED);
    }

    @GetMapping
    public List<Bet> getAllBets() {
        return betService.getAllBets();
    }

    @GetMapping(path = "getallbetsfromcurrentuser")
    public List<Bet> getAllBetsFromCurrentUser() throws BetException, UserException {
        return betService.getAllBetsFromCurrentUser();
    }

    @GetMapping(path = "getbybetid/{id}")
    public Bet findBetById(@PathVariable("id") Long id) throws BetException {
        return betService.findBetById(id);
    }

}
