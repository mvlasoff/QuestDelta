package ua.com.javarush.quest.khmelov.repository.memory;

import ua.com.javarush.quest.khmelov.entity.Question;

import java.util.Comparator;
import java.util.stream.Stream;

public class QuestionRepository extends AbstractRepository<Question> {

    @Override
    public Stream<Question> find(Question pattern) {
        return getAll()
                .filter(entity -> isOk(pattern, entity, Question::getId)
                        && isOk(pattern, entity, Question::getQuest)
                        && isOk(pattern, entity, Question::getImage)
                        && isOk(pattern, entity, Question::getText)
                        && isOk(pattern, entity, Question::getAnswers)
                        && isOk(pattern, entity, Question::getGameState)
                )
                .sorted(Comparator.comparingLong(Question::getId));
    }

}
