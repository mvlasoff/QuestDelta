package ua.com.javarush.quest.khmelov.questdelta.repository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;
import ua.com.javarush.quest.khmelov.questdelta.entity.SpaceQuest;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestRepositoryTest {
    QuestRepository questRepository = QuestRepository.get();

    @Test
    void whenStaticGetThenReturnQuestRepo() throws NoSuchFieldException, IllegalAccessException {
        Field field = QuestRepository.class.getDeclaredField("questRepository");
        field.setAccessible(true);
        QuestRepository actualRep = (QuestRepository) field.get(questRepository);
        assertEquals(QuestRepository.get(), actualRep);
    }

    @Test
    void whenInvokeGetAllThenGetAll() {
        QuestRepository mock = Mockito.mock(QuestRepository.class);
        mock.get(1L);
        Mockito.verify(mock).get(1L);
    }

    @Test
    void whenInvokeGetStartQuestionThenGetStartQuestion() {
        Question actualStartQuestion = questRepository.getStartQuestion(1L);
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        Question expectedStartQuestion = questRepository.get(1L, 11L).get();
        assertEquals(expectedStartQuestion, actualStartQuestion);
    }

    @Test
    void whenInvokeGetAnswersThenGetAnswers() {
        @SuppressWarnings("OptionalGetWithoutIsPresent")
        boolean actual = new SpaceQuest().get(11L).get().getAnswersSet()
                .containsAll(questRepository.get(1L, 11L).get().getAnswersSet());
        assertTrue(actual);
    }
}