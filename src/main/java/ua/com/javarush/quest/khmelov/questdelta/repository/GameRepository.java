package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.Answer;
import ua.com.javarush.quest.khmelov.questdelta.entity.Game;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameRepository {
    private static final GameRepository GAME_REPOSITORY = new GameRepository();
    private final Map<Long, Game> games;

    private GameRepository() {
        games = new HashMap<>();
        games.put(1L, new Game());
        games.put(2L, new Game());
    }

    public static GameRepository get() {
        return GAME_REPOSITORY;
    }

    public Map<Long, Game> getGames() {
        return games;
    }

    public int getGamesCount(long gameId) {
        return games.get(gameId).getGamesCount();
    }

    public int getGamesWon(long gameId) {
        return games.get(gameId).getGamesWon();
    }

    public void setGamesCount(long gameId) {
        games.get(gameId).setGamesCount();
    }

    public void setGamesWon(long gameId) {
        games.get(gameId).setGamesWon();
    }
}