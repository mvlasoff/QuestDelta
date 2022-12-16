package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UserTest extends BaseEntityTest {


    @Test
    void find() {
        User user = session.get(User.class, 1L);
        Assertions.assertEquals("Ivan", user.getLogin());
    }

}