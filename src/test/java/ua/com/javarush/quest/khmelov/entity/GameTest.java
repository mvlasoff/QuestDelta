package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest extends BaseEntityTest{

    @Test
    void find(){
        Game game = session.find(Game.class, 1L);
        System.out.println(game);
    }

}