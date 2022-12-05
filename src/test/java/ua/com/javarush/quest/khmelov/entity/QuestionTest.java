package ua.com.javarush.quest.khmelov.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest extends BaseEntityTest{

    @Test
    void find() {
        Question question = session.get(Question.class, 1L);
        System.out.println(question);
    }

}