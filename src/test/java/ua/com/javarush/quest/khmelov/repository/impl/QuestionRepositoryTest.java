package ua.com.javarush.quest.khmelov.repository.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.GameState;
import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Container;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestionRepositoryTest {
    @BeforeAll
    static void init(){
        Container.init();
    }
    private final Repository<Question> questionRepository = Winter.getBean(QuestionRepository.class);
    private final Repository<Quest> questRepository = Winter.getBean(QuestRepository.class);

    public static Stream<Arguments> getSamplePatternForSearch() {
        return Stream.of(
                Arguments.of(Question.with().id(0L).build(), 1),
                Arguments.of(Question.with().id(1234567890L).build(), 0),
                Arguments.of(Question.with().text("test_text").build(), 1)
        );
    }

    @Test
    @DisplayName("When get all then count>0")
    void getAll() {
        long count = questionRepository.getAll().count();
        assertTrue(count > 0);
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternForSearch")
    @DisplayName("Check find by not null fields")
    public void find(Question question, int count) {
        long actualCount = questionRepository.find(question).count();
        assertEquals(count, actualCount);
    }

    @Test
    void get() {
        Question question = questionRepository.get(0L);
        assertEquals(question.getText(), "test_text");
    }

    @Test
    @DisplayName("When create+update+delete tempQuest then no Exception")
    void createUpdateDelete() {
        Quest quest = questRepository.get(0L);
        Question question = Question.with().text("test_text_cud").quest(quest).gameState(GameState.PLAY).build();
        questionRepository.create(question);

        question.setText("test_text_cud_update");
        questionRepository.update(question);

        questionRepository.delete(question);
        assertTrue(question.getId() > 0);
    }
}