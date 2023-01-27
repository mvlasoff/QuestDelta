package ua.com.javarush.quest.khmelov.questdelta.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameStatistics {

    private Long id;

    private User user;

    private List<Game> games = new ArrayList<>();

    public GameStatistics() {
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @SuppressWarnings("unused")
    public int getGamesCount(long gameId) {
        return games.get((int) gameId).getGamesCount();
    }

    @SuppressWarnings("unused")
    public int getGamesWon(long gameId) {
        return games.get((int) gameId).getGamesWon();
    }

    public void setGamesCount(long gameId) {
        Game game = games.get((int) gameId);
        Integer gamesCount = game.getGamesCount();
        setGamesCount(++gamesCount);
    }

    public void setGamesWon(long gameId) {
        Game game = games.get((int) gameId);
        Integer gamesWon = game.getGamesWon();
        setGamesWon(++gamesWon);
    }
}