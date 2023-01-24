package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.HashMap;
import java.util.Map;

public class GameStatistics {
    private final Map<Long, Game> games;

    public GameStatistics() {
        games = new HashMap<>();
        games.put(1L, new Game());
        games.put(2L, new Game());
    }

    public Map<Long, Game> getGames() {
        return games;
    }

    @SuppressWarnings("unused")
    public int getGamesCount(long gameId) {
        return games.get(gameId).getGamesCount();
    }

    @SuppressWarnings("unused")
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