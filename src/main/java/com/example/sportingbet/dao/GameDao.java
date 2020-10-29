package com.example.sportingbet.dao;

import com.example.sportingbet.model.Game;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GameDao {
    int insertGame(UUID number, Game game);
    default int insertGame(Game game){
        UUID number=UUID.randomUUID();
        return insertGame(number,game);
    }

    List<Game> selectAllGame();
    Optional<Game> selectGameByNumber(UUID number);
    int deleteGameByNumber(UUID number);
    int updateGameByNumber(UUID number, Game game);
}
