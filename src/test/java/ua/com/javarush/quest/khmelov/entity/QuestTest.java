package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Hibernate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class QuestTest extends BaseEntityTest{

    @Test
    void find() {
        //session.enableFetchProfile("quest_sub_select_question");
        Quest quest = session.get(Quest.class, 1234567890L);
        Assertions.assertNull(quest);
    }

}