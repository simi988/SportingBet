package com.example.sportingbet.dao;

import com.example.sportingbet.model.Bet;

import java.util.List;
import java.util.UUID;

public interface BetDao {
    double calculateGain(Bet bet);

    int insertBet(UUID id, Bet bet);

    default int insertBet(Bet bet) {
        UUID id = UUID.randomUUID();
        return insertBet(id, bet);
    }

    List<Bet> selectAllBet();
}
