package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class D_QuestionManyToOneOneDirectTest extends A_BaseHibernateTest {

    @Test
    void find() {
        session.beginTransaction();
        Question question = session.find(Question.class, 1L);
        System.out.println(question);
        Quest quest = question.getQuest();
        System.out.println(quest);
        assertEquals("Проверим арифметику",quest.getName());
        assertEquals("Знаешь арифметику?",question.getText());
        session.getTransaction().commit();
    }

}
