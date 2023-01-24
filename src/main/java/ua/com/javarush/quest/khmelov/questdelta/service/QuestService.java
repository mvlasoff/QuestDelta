package ua.com.javarush.quest.khmelov.questdelta.service;

import ua.com.javarush.quest.khmelov.questdelta.entity.Answer;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;
import ua.com.javarush.quest.khmelov.questdelta.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public class QuestService {
    private final QuestRepository questRepository;
    private static final QuestService questService = new QuestService();

    private QuestService() {
        questRepository = QuestRepository.get();
    }

    public static QuestService getQuestService() {
        return questService;
    }

    @SuppressWarnings("unused")
    public Collection<Question> get(long questId) {
        return questRepository.get(questId);
    }

    public Optional<Question> get(long questId, long questionId) {
        return questRepository.get(questId, questionId);
    }

    public Question getStartQuestion(long questId) {
        return questRepository.getStartQuestion(questId);
    }

    public Collection<Answer> getAnswers(long questId, Question question) {
        return questRepository.getAnswers(questId, question);
    }
}
