package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class C_UserOneToManyTest extends A_BaseHibernateTest {



    @Test
    void find() {
        session.beginTransaction();
        User user = session.find(User.class, 1L);
        Collection<Quest> quests = user.getQuests();
        quests.forEach(System.out::println);
        assertEquals(3,quests.stream().count());
        session.getTransaction().commit();
    }

}