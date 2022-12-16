package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

class QuestTest extends BaseEntityTest{

    @Test
    void find() {
        session.enableFetchProfile("quest_sub_select_question");
        Quest quest = session.load(Quest.class, 123456789L);
        Assertions.assertNull(quest.id);
//        System.out.println(quest);
//        Collection<User> players = quest.getPlayers();
//        System.out.println(players);
    }

}