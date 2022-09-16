package ua.com.javarush.quest.khmelov.questdelta.service;

import ua.com.javarush.quest.khmelov.questdelta.entity.Node;
import ua.com.javarush.quest.khmelov.questdelta.repository.QuestRepository;

import java.util.Collection;
import java.util.Optional;

public class QuestService {
    private final QuestRepository questRepository;
    private static QuestService questService;

    private QuestService() {
        questRepository = new QuestRepository();
    }

    public static QuestService getNodeService() {
        if (questService == null) {
            questService = new QuestService();
        }
        return questService;
    }

    public Collection<Node> getAll() {
        return questRepository.getAll();
    }

    public Optional<Node> get(long id) {
        return questRepository.get(id);
    }
}
