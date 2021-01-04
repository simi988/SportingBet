package com.example.sportingbet.dao;

import com.example.sportingbet.entity.BetDO;
import com.example.sportingbet.entity.UserDO;
import com.example.sportingbet.exception.BetException;
import com.example.sportingbet.model.Bet;

import java.util.List;

public interface BetDAO {
    void addBet(BetDO betDO);

    List<Bet> getAllBets();

    List<Bet> getAllBetsFromCurrentUser(UserDO userDO) throws BetException;

    Bet findBetById(Long id) throws BetException;
}
