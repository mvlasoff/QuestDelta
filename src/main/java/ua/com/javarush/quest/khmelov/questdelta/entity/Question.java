package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.*;

public class Question {
    private final long id;
    private final String question;
    private final boolean isFinal;

    private final Set<Answer> answersSet;


    public Question(long id, String question, boolean isFinal) {
        this.id = id;
        this.question = question;
        this.isFinal = isFinal;
        answersSet = new HashSet<>();
    }

    public String getQuestion() {
        return question;
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
        return Objects.equals(question, question1.question);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question);
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                '}';
    }
}
