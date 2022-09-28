package ua.com.javarush.quest.khmelov.questdelta.service;

import ua.com.javarush.quest.khmelov.questdelta.repository.GameRepository;
import ua.com.javarush.quest.khmelov.questdelta.repository.QuestRepository;

public class GameService {
    private final GameRepository gameRepository;
    private static GameService gameService;

    private GameService() {
        gameRepository = GameRepository.get();
    }

    public static GameService getGameService() {
        if (gameService == null) {
            gameService = new GameService();
        }
        return gameService;
    }

    public GameRepository getGameRepository() {
        return gameRepository;
    }

    public int getGamesCount(long gameId) {
        return gameRepository.getGamesCount(gameId);
    }

    public int getGamesWon(long gameId) {
        return gameRepository.getGamesWon(gameId);
    }

    public void setGamesCount(long gameId) {
        gameRepository.setGamesCount(gameId);
    }

    public void setGamesWon(long gameId) {
        gameRepository.setGamesWon(gameId);
    }
}
