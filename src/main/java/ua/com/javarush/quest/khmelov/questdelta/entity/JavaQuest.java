package ua.com.javarush.quest.khmelov.questdelta.entity;

import jakarta.servlet.annotation.WebServlet;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

public class JavaQuest implements Quest {
    private final String questName = "Java Quest";
    private final Question startQuestion;
    private final HashMap<Long, Question> questions;


    public JavaQuest(Question startQuestion, HashMap<Long, Question> questions) {
        this.startQuestion = startQuestion;
        this.questions = questions;
    }


    @Override
    public String getQuestName() {
        return null;
    }

    @Override
    public Collection<Question> getAll() {
        return null;
    }

    @Override
    public Optional<Question> get(long id) {
        return Optional.empty();
    }

    @Override
    public Question getStartQuestion() {
        return null;
    }

    @Override
    public Collection<Answer> getAnswers(Question question) {
        return null;
    }
}
