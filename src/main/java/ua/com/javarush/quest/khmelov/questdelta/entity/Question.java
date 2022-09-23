package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.*;

public class Question {
    private final long id;
    private final String text;
    private final boolean isFinal;

    private final Set<Answer> answersSet;


    public Question(long id, String text, boolean isFinal) {
        this.id = id;
        this.text = text;
        this.isFinal = isFinal;
        answersSet = new HashSet<>();
    }

    public String getText() {
        return text;
    }

    public long getId() {
        return id;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public Set<Answer> getAnswersSet() {
        return answersSet;
    }

    public void addAnswers(Answer... answers) {
        Collections.addAll(answersSet, answers);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question1 = (Question) o;
        return Objects.equals(text, question1.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + text + '\'' +
                '}';
    }
}
