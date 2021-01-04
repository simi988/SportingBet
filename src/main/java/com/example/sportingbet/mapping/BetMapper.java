package com.example.sportingbet.mapping;

import com.example.sportingbet.entity.BetDO;
import com.example.sportingbet.model.Bet;

public class BetMapper {


    public Bet mapToDto(BetDO betDO) {
        Bet bet = new Bet();
        bet.setId(betDO.getId());
        bet.setEventList(betDO.getEventList());
        bet.setSum(betDO.getSum());
        bet.setOdd(betDO.getOdd());
        return bet;
    }

    public BetDO mapToDO(Bet bet) {
        BetDO betDO = new BetDO();
        betDO.setEventList(bet.getEventList());
        betDO.setOdd(bet.getOdd());
        betDO.setSum(bet.getSum());
        return betDO;
    }
}
