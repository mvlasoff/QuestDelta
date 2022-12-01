package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class B_QuestManyToOneTest extends A_BaseHibernateTest {

    @Test
    void find() {
        session.beginTransaction();
        Quest quest = session.find(Quest.class, 1L);
        System.out.println(quest);
        assertEquals("Проверим арифметику", quest.getName());
        User user = quest.getUser();
        System.out.println(user);
        assertEquals("vanya", user.getLogin());
        session.getTransaction().commit();
    }

}