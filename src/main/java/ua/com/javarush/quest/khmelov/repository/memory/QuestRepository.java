package ua.com.javarush.quest.khmelov.repository.memory;

import ua.com.javarush.quest.khmelov.entity.Quest;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.Comparator;
import java.util.stream.Stream;

public class QuestRepository extends AbstractRepository<Quest> {

    @Override
    public Stream<Quest> find(Quest pattern) {
        return map.values().stream()
                .filter(entity -> isOk(pattern, entity, Quest::getId)
                        && isOk(pattern, entity, Quest::getAuthorId)
                        && isOk(pattern, entity, Quest::getStartQuestionId)
                        && isOk(pattern, entity, Quest::getName)
                        && isOk(pattern, entity, Quest::getText)
                        && isOk(pattern, entity, Quest::getQuestions)
                )
                .sorted(Comparator.comparingLong(Quest::getId));
    }

}
