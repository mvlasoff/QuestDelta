package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserInfoTest extends BaseEntityTest {

    @Test
    void find() {
        Transaction tx = session.getTransaction();
        tx.begin();
        User user = session.get(User.class, 1L);
        Assertions.assertEquals("Urban street 11 23", user.getUserInfo().getAddress());
        tx.rollback();
    }

}