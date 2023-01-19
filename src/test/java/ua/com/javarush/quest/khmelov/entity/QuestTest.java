package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class QuestTest extends BaseEntityTest {

    @Test
    void find() {
        Transaction tx = session.getTransaction();
        tx.begin();
        Quest quest = session.get(Quest.class, 1234567890L);
        Assertions.assertNull(quest);
        tx.rollback();
    }

}