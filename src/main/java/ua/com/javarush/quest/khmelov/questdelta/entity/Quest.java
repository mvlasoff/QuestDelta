package ua.com.javarush.quest.khmelov.questdelta.entity;

import java.util.Collection;
import java.util.Optional;

public interface Quest {

    @SuppressWarnings("unused")
    String getQuestName();

    Collection<Question> getAll();

    Optional<Question> get(long id);

    Question getStartQuestion();

    Collection<Answer> getAnswers(Question question);
}