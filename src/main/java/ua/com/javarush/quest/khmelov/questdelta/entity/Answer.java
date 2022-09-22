package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.Objects;

public class Answer {
    private final long id;
    private final String answer;
    private final Question question;

    public Answer(String answer, Question question) {
        this.answer = answer;
        this.question = question;
        this.id = question.getId();
    }

    public long getId() {
        return id;
    }

    public Question getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer1 = (Answer) o;
        return Objects.equals(answer, answer1.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answer='" + answer + '\'' +
                '}';
    }
}
