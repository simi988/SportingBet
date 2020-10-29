package com.example.sportingbet.model;

public class GameType {
    private final double share;
    private final  Enum TYPE;

    public GameType(double share, Enum type) {
        this.share = share;
        TYPE = type;

    }

    public double getShare() {
        return share;
    }

    public Enum getTYPE() {
        return TYPE;
    }
}
