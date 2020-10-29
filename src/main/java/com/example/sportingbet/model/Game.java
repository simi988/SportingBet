package com.example.sportingbet.model;

import java.util.UUID;

public class Game {
    private final UUID number;
    private final String name;
    private final GameType type;

    public Game(UUID number, String name, GameType type) {
        this.number = number;
        this.name = name;
        this.type = type;
    }

    public UUID getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public GameType getType() {
        return type;
    }
}
