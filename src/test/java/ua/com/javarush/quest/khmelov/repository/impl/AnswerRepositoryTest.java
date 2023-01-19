package ua.com.javarush.quest.khmelov.repository.impl;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.Answer;
import ua.com.javarush.quest.khmelov.entity.GameState;
import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.repository.Container;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnswerRepositoryTest {
    @BeforeAll
    static void init(){
        Container.init();
    }
    private final Repository<Answer> answerRepository = Winter.getBean(AnswerRepository.class);
    private final Repository<Question> questionRepository = Winter.getBean(QuestionRepository.class);

    public static Stream<Arguments> getSamplePatternForSearch() {
        return Stream.of(
                Arguments.of(Answer.with().id(0L).build(), 1),
                Arguments.of(Answer.with().id(1234567890L).build(), 0),
                Arguments.of(Answer.with().text("test_text").build(), 1)
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
    public void find(Answer question, int count) {
        long actualCount = answerRepository.find(question).count();
        assertEquals(count, actualCount);
    }

    @Test
    void get() {
        Answer question = answerRepository.get(0L);
        assertEquals(question.getText(), "test_text");
    }

    @Test
    @DisplayName("When create+update+delete tempQuest then no Exception")
    void createUpdateDelete() {
        Answer answer = Answer.with().text("test_text_cud")
                .questionId(0L).build();
        answerRepository.create(answer);

        answer.setText("test_text_cud_update");
        answerRepository.update(answer);

        answerRepository.delete(answer);
        assertTrue(answer.getId() > 0);
    }
}