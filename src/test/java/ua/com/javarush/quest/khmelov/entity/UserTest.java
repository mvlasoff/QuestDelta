package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest extends BaseEntityTest {


    @Test
    void find() {
        Transaction tx = session.getTransaction();
        tx.begin();
        User user = session.get(User.class, 0L);
        Assertions.assertEquals("test_user", user.getLogin());
        tx.rollback();
    }

}