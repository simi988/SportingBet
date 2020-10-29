package com.example.sportingbet.api;

import com.example.sportingbet.model.Bet;
import com.example.sportingbet.service.BetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/v1/bet")
@RestController
public class BetController {
private static BetService betService;
    @Autowired
    public BetController(BetService betService) {
        this.betService=betService;
    }
    @PostMapping
    public void addBet(@Valid @NotNull @RequestBody Bet bet){
        betService.addBet(bet);
    }
    @GetMapping
    public List<Bet> getAllBet(){
        return betService.getAllBet();
    }
    @GetMapping
    public double calculateGain(Bet bet){
        return betService.calculateGain(bet);
    }
}
