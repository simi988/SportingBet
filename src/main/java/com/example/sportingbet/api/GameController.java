package com.example.sportingbet.api;

import com.example.sportingbet.model.Game;
import com.example.sportingbet.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/game")
@RestController
public class GameController {
    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public void addGame(@Valid @NotNull @RequestBody Game game) {
        gameService.addGame(game);
    }

    @GetMapping
    public List<Game> getAllGame() {
        return gameService.getAllGame();
    }

    @GetMapping(path = "{number}")
    public Game getGameByNumber(@PathVariable("number") UUID number) {
        return gameService.getGameByNumber(number)
                .orElse(null);
    }

    @DeleteMapping(path = "{number}")
    public void deleteGameByNumber(@PathVariable("number") UUID number) {
        gameService.deleteGame(number);
    }

    @PutMapping(path = "{number}")
    public void updateGame(@PathVariable("number") UUID number, @Valid @NotNull @RequestBody Game gameToUpdate) {
        gameService.updateGame(number, gameToUpdate);
    }
}
