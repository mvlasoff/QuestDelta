package ua.com.javarush.quest.khmelov.repository.impl;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuestRepositoryTest {

    private final Repository<User> userRepository = Winter.getBean(UserRepository.class);
    private final Repository<Quest> questRepository = Winter.getBean(QuestRepository.class);

    public static Stream<Arguments> getSamplePatternForSearch() {
        return Stream.of(
                Arguments.of(Quest.with().id(0L).build(), 1),
                Arguments.of(Quest.with().id(1234567890L).build(), 0),
                Arguments.of(Quest.with().text("test_text").build(), 1)
        );
    }

    @Test
    @DisplayName("When get all then count>0")
    void getAll() {
        long count = questRepository.getAll().count();
        assertTrue(count > 0);
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternForSearch")
    @DisplayName("Check find by not null fields")
    public void find(Quest user, int count) {
        long actualCount = questRepository.find(user).count();
        assertEquals(count, actualCount);
    }

    @Test
    void get() {
        Quest quest = questRepository.get(0L);
        assertEquals(quest.getText(), "test_text");
    }

    @Test
    @DisplayName("When create+update+delete tempQuest then no Exception")
    void createUpdateDelete() {
        User user = userRepository.get(1L);
        Quest testQuest = Quest.with()
                .name("test_name_cud")
                .text("test_text_cud")
                .startQuestionId(0L)
                .user(user)
                .build();
        questRepository.create(testQuest);

        testQuest.setName("test_name_cud_update");
        questRepository.update(testQuest);

        questRepository.delete(testQuest);
        assertTrue(testQuest.getId() > 0);
    }
}