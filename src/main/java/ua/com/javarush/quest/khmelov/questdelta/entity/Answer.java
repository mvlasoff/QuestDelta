package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.Objects;

public class Answer {
    private final long id;
    private final String text;
    private final Question question;

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
        this.id = question.getId();
    }

    public long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(text, answer1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + text + '\'' +
                '}';
    }
}
