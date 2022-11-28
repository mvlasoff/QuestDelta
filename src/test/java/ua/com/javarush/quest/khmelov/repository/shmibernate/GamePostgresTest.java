package ua.com.javarush.quest.khmelov.repository.shmibernate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.com.javarush.quest.khmelov.entity.Game;
import ua.com.javarush.quest.khmelov.entity.GameState;
import ua.com.javarush.quest.khmelov.repository.Repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GamePostgresTest {
    private final Repository<Game> gameRepository= PostgresRepository.get(Game.class);

    @Test
    @DisplayName("When create+update+delete tempGame then no Exception")
    void createUpdateDelete() {
        Game tempGame = Game.with()
                .gameState(GameState.PLAY)
                .questId(12L)
                .userId(1L)
                .build();
        gameRepository.create(tempGame);
        System.out.println("CREATE " + tempGame);

        tempGame.setGameState(GameState.WIN);
        gameRepository.update(tempGame);
        System.out.println("UPDATE " + tempGame);

        gameRepository.delete(tempGame);
        System.out.println("DELETE " + tempGame);
        assertTrue(tempGame.getId() > 0);
    }
}