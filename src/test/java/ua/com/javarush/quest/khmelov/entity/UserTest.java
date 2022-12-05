package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import java.util.Collection;

class UserTest extends BaseEntityTest {


    @Test
    void find() {
        User user = session.get(User.class, 1L);
        System.out.println(user);
        Collection<Quest> quests = user.getQuests();
        System.out.println(quests);
        Collection<Quest> questsInGame = user.getQuestsInGame();
        System.out.println(questsInGame);
    }

}