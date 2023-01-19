package ua.com.javarush.quest.khmelov.entity;

import org.hibernate.Transaction;
import org.junit.jupiter.api.Test;

class QuestionTest extends BaseEntityTest {

    @Test
    void find() {
        Transaction tx = session.getTransaction();
        tx.begin();
        Question question = session.get(Question.class, 0L);
        System.out.println(question);
        tx.rollback();
    }

}