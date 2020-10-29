package com.example.sportingbet.model;

import java.util.List;
import java.util.UUID;

public class Bet {
    private UUID id;
    private List<Game> gameList;
    private double sum;

    public Bet(UUID id, List<Game> gameList, double sum) {
        this.id = id;
        this.gameList = gameList;
        this.sum = sum;
    }

    public double getSum() {
        return sum;
    }

    public List<Game> getGameList() {
        return gameList;
    }
}
