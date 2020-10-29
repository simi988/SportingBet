package com.example.sportingbet.dao;

import com.example.sportingbet.model.Bet;
import com.example.sportingbet.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeBetDao")
public class FakeBetDAOService implements BetDao {
    private static List<Bet> DB = new ArrayList<>();

    @Override
    public double calculateGain(Bet bet) {
        double share = calculateTicketShare(bet);
        double sum = bet.getSum();
        return share * sum;
    }

    @Override
    public int insertBet(UUID id, Bet bet) {
        DB.add(new Bet(id, bet.getGameList(), bet.getSum()));
        return 1;

    }

    @Override
    public List<Bet> selectAllBet() {
        return DB;
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
