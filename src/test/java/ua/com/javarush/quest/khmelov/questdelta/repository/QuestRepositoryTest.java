package ua.com.javarush.quest.khmelov.questdelta.repository;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;
import ua.com.javarush.quest.khmelov.questdelta.entity.SpaceQuest;

import java.lang.reflect.Field;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class QuestRepositoryTest {
    @Test
    void whenStaticGetThenReturnQuestRepo() throws NoSuchFieldException, IllegalAccessException {
        QuestRepository questRepository = QuestRepository.get();
        Field field = QuestRepository.class.getDeclaredField("questRepository");
        field.setAccessible(true);
        QuestRepository actualRep = (QuestRepository) field.get(questRepository);
        assertEquals(QuestRepository.get(), actualRep);
    }

    @Test
    void whenInvokeGetAllThenGetAll() {
        QuestRepository questRepository = QuestRepository.get();
        QuestRepository mock = Mockito.mock(QuestRepository.class);
        mock.getAll(1L);
        Mockito.verify(mock).getAll(1L);
    }
}