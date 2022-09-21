package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.Answer;
import ua.com.javarush.quest.khmelov.questdelta.entity.Quest;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;

import java.util.Collection;
import java.util.Optional;

public class QuestRepository {
    private final Quest quest;

    public QuestRepository(Quest quest) {
        this.quest = quest;
    }

    public Collection<Question> getAll() {
        return quest.getAll();
    }

    public Optional<Question> get(long id) {
        return quest.get(id);
    }

    public Question getStartQuestion() {
        return quest.getStartQuestion();
    }

    public Collection<Answer> getAnswers(Question question) {
        return quest.getAnswers(question);
    }
}
