package ua.com.javarush.quest.khmelov.repository.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.Game;
import ua.com.javarush.quest.khmelov.entity.GameState;
import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.repository.Container;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameRepositoryTest {
    @BeforeAll
    static void init(){
        Container.init();
    }
    private final Repository<Game> answerRepository = Winter.getBean(GameRepository.class);

    public static Stream<Arguments> getSamplePatternForSearch() {
        return Stream.of(
                Arguments.of(Game.with().id(0L).build(), 1),
                Arguments.of(Game.with().id(1234567890L).build(), 0),
                Arguments.of(Game.with().gameState(GameState.PLAY).build(), 1)
        );
    }

    @Test
    @DisplayName("When get all then count>0")
    void getAll() {
        long count = answerRepository.getAll().count();
        assertTrue(count > 0);
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternForSearch")
    @DisplayName("Check find by not null fields")
    public void find(Game question, int count) {
        long actualCount = answerRepository.find(question).count();
        assertEquals(count, actualCount);
    }

    @Test
    void get() {
        Game game = answerRepository.get(0L);
        assertEquals(game.getGameState(), GameState.PLAY);
    }

    @Test
    @DisplayName("When create+update+delete tempQuest then no Exception")
    void createUpdateDelete() {
        Game game = Game.with().gameState(GameState.PLAY)
                .questId(0L).userId(0L).build();
        answerRepository.create(game);

        game.setGameState(GameState.LOST);
        answerRepository.update(game);

        answerRepository.delete(game);
        assertTrue(game.getId() > 0);
    }
}