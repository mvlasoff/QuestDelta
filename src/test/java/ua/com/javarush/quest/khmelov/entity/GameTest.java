package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

class GameTest extends BaseEntityTest {

    @Test
    void find() {
        Transaction tx = session.getTransaction();
        tx.begin();
        Game game = session.find(Game.class, 0L);
        System.out.println(game);
        tx.rollback();
    }

}