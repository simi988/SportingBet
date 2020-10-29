package com.example.sportingbet.service;

import com.example.sportingbet.dao.BetDao;
import com.example.sportingbet.model.Bet;
import com.example.sportingbet.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BetService {
    private final BetDao betDao;

    @Autowired
    public BetService(@Qualifier("fakeBetDao") BetDao betDao) {
        this.betDao = betDao;
    }

    public int addBet(Bet bet) {
        return betDao.insertBet(bet);
    }

    public double calculateGain(Bet bet) {
        double share = calculateTicketShare(bet);
        double sum = bet.getSum();
        return share * sum;
    }
    public List<Bet> getAllBet(){
      return betDao.selectAllBet();
    }

    private double calculateTicketShare(Bet bet) {
        List<Game> gameList = bet.getGameList();
        double totalShare = 0;
        for (Game game : gameList) {
            double share = game.getType().getShare();
            totalShare *= share;
        }
        return totalShare;
    }
}
