package com.example.sportingbet.service;

import com.example.sportingbet.dao.GameDao;
import com.example.sportingbet.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GameService {

    private final GameDao gameDao;

    @Autowired
    public GameService(@Qualifier("fakeGameDao") GameDao gameDao) {
        this.gameDao = gameDao;
    }
    public int addGame(Game game){
        return gameDao.insertGame(game);
    }

    public List<Game> getAllGame(){
        return gameDao.selectAllGame();
    }

    public Optional<Game> getGameByNumber(UUID number){
        return gameDao.selectGameByNumber(number);
    }

    public int deleteGame(UUID number){
        return gameDao.deleteGameByNumber(number);
    }

    public int updateGame(UUID number, Game newGame){
        return gameDao.updateGameByNumber(number, newGame);
    }

}

