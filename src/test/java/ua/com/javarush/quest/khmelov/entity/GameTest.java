package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

class GameTest extends BaseEntityTest {

    @Test
    void find() {
        Game game = session.find(Game.class, 1L);
        System.out.println(game);
    }

}