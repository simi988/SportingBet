package com.example.sportingbet.dao;

import com.example.sportingbet.model.Client;
import com.example.sportingbet.model.Game;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("fakeGameDao")
public class FakeGameDAOService implements GameDao{
    private static List<Game> DB=new ArrayList<>();
    @Override
    public int insertGame(UUID number, Game game) {
        DB.add(new Game(number,game.getName(),game.getType()));
        return 1;
    }

    @Override
    public List<Game> selectAllGame() {
        return DB;
    }

    @Override
    public Optional<Game> selectGameByNumber(UUID number) {
        return DB.stream().filter(game -> game.getNumber().equals(number))
                .findFirst();
    }

    @Override
    public int deleteGameByNumber(UUID number) {
        Optional<Game> deleteGame=selectGameByNumber(number);
        if (deleteGame.isEmpty()){
            return 0;
        }
        DB.remove(deleteGame.get());
        return 1;
    }

    @Override
    public int updateGameByNumber(UUID number, Game updateGame) {
        return selectGameByNumber(number)
                .map(client->{
                    int indexOfClientToUpdate=DB.indexOf(client);
                    if (indexOfClientToUpdate>=0){
                        DB.set(indexOfClientToUpdate,new Game(number,updateGame.getName(),updateGame.getType()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
