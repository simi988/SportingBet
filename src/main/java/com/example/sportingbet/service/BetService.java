package com.example.sportingbet.service;

import com.example.sportingbet.exception.BetException;
import com.example.sportingbet.exception.UserException;
import com.example.sportingbet.model.Bet;

import java.util.List;

public interface BetService {
    void addBet(Bet bet) throws BetException;

    List<Bet> getAllBets();

    List<Bet> getAllBetsFromCurrentUser() throws BetException, UserException;

    Bet findBetById(Long id) throws BetException;
}
