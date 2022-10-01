package ua.com.javarush.quest.khmelov.questdelta.entity;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
class AnswerTest {

    @ParameterizedTest
    @ValueSource(strings = {"asd", "adssd", "12313 qwerjgwe", "zxzxczc", ".,m,.m bmnb iouoi %$%^&&^%%%", "OP[TYUOTYY TY[UOTYUTYU"})
    void whenAnswerIsSetThenGetTextEquals(String text) {
        Answer answer = new Answer(text, new Question(1L, "qwerty", false, null));
        String actual = answer.getText();
        assertEquals(text, actual);
    }

    @ParameterizedTest
    @ValueSource(longs = {Long.MIN_VALUE, -123123123, -1, 0, 12735123, Long.MAX_VALUE})
    void whetAnswerIsSetThenGetIdEquals(long id) {
        Answer answer = new Answer("text", new Question(id, "qwerty", false, null));
        long actual = answer.getId();
        assertEquals(id, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"asd", "adssd", "12313 qwerjgwe", "zxzxczc", ".,m,.m bmnb iouoi %$%^&&^%%%", "OP[TYUOTYY TY[UOTYUTYU"})
    void whetAnswerIsSetThenAnswersEquals(String text) {
        Answer answer1 = new Answer(text, new Question(1L, "qwerty", false, null));
        Answer answer2 = new Answer(text, new Question(2L, "iuertyert", true, null));

        assertEquals(answer2, answer1);
    }
}