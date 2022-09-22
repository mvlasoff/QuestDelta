package ua.com.javarush.quest.khmelov.repository;

import ua.com.javarush.quest.khmelov.entity.Quest;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class QuestRepository extends AbstractRepository<Quest> implements Repository<Quest> {

    private static final QuestRepository userRepository = new QuestRepository();

    public static QuestRepository get() {
        return userRepository;
    }

    private QuestRepository() {
        map.put(1L, Quest.with().name("TestQuest").build());
    }


    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values().stream()
                .filter(entity -> isOk(pattern, entity, Quest::getId)
                        && isOk(pattern, entity, Quest::getAuthorId)
                        && isOk(pattern, entity, Quest::getQuestions)
                        && isOk(pattern, entity, Quest::getName)
                )
                .sorted(Comparator.comparingLong(Quest::getId));
    }

}
