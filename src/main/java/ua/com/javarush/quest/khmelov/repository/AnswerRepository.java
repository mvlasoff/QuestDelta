package ua.com.javarush.quest.khmelov.repository;

import ua.com.javarush.quest.khmelov.entity.Answer;

import java.util.Collection;
import java.util.Comparator;

public class AnswerRepository extends AbstractRepository<Answer> implements Repository<Answer> {

    public static final AnswerRepository userRepository = new AnswerRepository();

    public static AnswerRepository get() {
        return userRepository;
    }

    private AnswerRepository() {
    }


    @Override
    public Collection<Answer> find(Answer pattern) {
        return map.values().stream()
                .filter(entity -> isOk(pattern, entity, Answer::getId)
                        && isOk(pattern, entity, Answer::getQuestionId)
                        && isOk(pattern, entity, Answer::getText)
                        && isOk(pattern, entity, Answer::getCorrect)
                )
                .sorted(Comparator.comparingLong(Answer::getId))
                .toList();
    }

}
