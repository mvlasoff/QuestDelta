package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class QuestRepository {

    private static final QuestRepository questRepository = new QuestRepository();
    private final Map<Long, Quest> quests;

    private QuestRepository() {
        quests = new HashMap<>();
        quests.put(1L, new SpaceQuest());
        quests.put(2L, new JavaQuest());
    }

    public static QuestRepository get() {
        return questRepository;
    }

    public Collection<Question> getAll(long questId) {
        return quests.get(questId).getAll();
    }

    public Optional<Question> get(long questId, long questionId) {
        return quests.get(questId).get(questionId);
    }

    public Question getStartQuestion(long questId) {
        return quests.get(questId).getStartQuestion();
    }

    public Collection<Answer> getAnswers(long questId, Question question) {
        return quests.get(questId).getAnswers(question);
    }
}
