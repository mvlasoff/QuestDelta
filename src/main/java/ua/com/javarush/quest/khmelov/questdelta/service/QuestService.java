package ua.com.javarush.quest.khmelov.questdelta.service;

import ua.com.javarush.quest.khmelov.questdelta.entity.Answer;
import ua.com.javarush.quest.khmelov.questdelta.entity.Quest;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;
import ua.com.javarush.quest.khmelov.questdelta.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public class QuestService {
    private final QuestRepository questRepository;
    private static QuestService questService;

    private QuestService(Quest quest) {
        questRepository = new QuestRepository(quest);
    }

    public static QuestService getQuestService(Quest quest) {
        if (questService == null) {
            questService = new QuestService(quest);
        }
        return questService;
    }

    public Collection<Question> getAll() {
        return questRepository.getAll();
    }

    public Optional<Question> get(long id) {
        return questRepository.get(id);
    }

    public Question getStartQuestion() {
        return questRepository.getStartQuestion();
    }

    public Collection<Answer> getAnswers(Question question) {
        return questRepository.getAnswers(question);
    }
}
