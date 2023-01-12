package ua.com.javarush.quest.khmelov.repository.impl;

import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.repository.BaseRepository;
import ua.com.javarush.quest.khmelov.repository.SessionCreator;

public class QuestRepository extends BaseRepository<Quest> {

    public QuestRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Quest.class);
    }
}
