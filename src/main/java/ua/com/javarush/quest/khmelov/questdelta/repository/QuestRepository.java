package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.Node;
import ua.com.javarush.quest.khmelov.questdelta.entity.SpaceQuest;
import ua.com.javarush.quest.khmelov.questdelta.entity.Quest;

import java.util.Collection;
import java.util.Optional;

public class QuestRepository {
    private final Quest quest = new SpaceQuest();

    public QuestRepository() {
    }

    public Collection<Node> getAll() {
        return quest.getAll();
    }

    public Optional<Node> get(long id) {
        return quest.get(id);
    }
}
