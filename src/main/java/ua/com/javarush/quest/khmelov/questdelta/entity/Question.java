package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.*;

public class Question {
    private final long id;
    private final String text;
    private final Set<Answer> answersSet;

    private final boolean isWon;
    private final String picture;


    public Question(long id, String text, boolean isWon, String picture) {
        this.id = id;
        this.text = text;
        this.isWon = isWon;
        this.picture = picture;
        answersSet = new HashSet<>();
    }

    public long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isWon() {
        return isWon;
    }

    public Set<Answer> getAnswersSet() {
        return answersSet;
    }

    public String getPicture() {
        return picture;
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
