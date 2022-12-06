package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import java.util.Collection;

class QuestTest extends BaseEntityTest{

    @Test
    void find() {
        session.enableFetchProfile("quest_sub_select_question");
        Quest quest = session.get(Quest.class, 1L);
        System.out.println(quest);
        Collection<User> players = quest.getPlayers();
        System.out.println(players);
    }

}