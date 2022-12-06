package ua.com.javarush.quest.khmelov.repository.memory;

import ua.com.javarush.quest.khmelov.entity.Answer;

import java.util.Comparator;
import java.util.stream.Stream;

public class AnswerRepository extends AbstractRepository<Answer> {

    @Override
    public Stream<Answer> find(Answer pattern) {
        return getAll()
                .filter(entity -> isOk(pattern, entity, Answer::getId)
                        && isOk(pattern, entity, Answer::getQuestionId)
                        && isOk(pattern, entity, Answer::getText)
                )
                .sorted(Comparator.comparingLong(Answer::getId));
    }

}
