package ua.com.javarush.quest.khmelov.questdelta.entity;

public class Game {
    private int gamesCount;
    private int gamesWon;

    public int getGamesCount() {
        return gamesCount;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesCount() {
        this.gamesCount++;
    }

    public void setGamesWon() {
        this.gamesWon++;
    }
}
