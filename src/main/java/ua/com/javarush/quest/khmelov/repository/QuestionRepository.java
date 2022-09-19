package ua.com.javarush.quest.khmelov.repository;

import ua.com.javarush.quest.khmelov.entity.Question;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class QuestionRepository extends AbstractRepository<Question> implements Repository<Question> {

    public static final QuestionRepository userRepository = new QuestionRepository();

    public static QuestionRepository get() {
        return userRepository;
    }

    private QuestionRepository() {
    }


    @Override
    public Stream<Question> find(Question pattern) {
        return map.values().stream()
                .filter(entity -> isOk(pattern, entity, Question::getId)
                        && isOk(pattern, entity, Question::getQuestId)
                        && isOk(pattern, entity, Question::getAnswers)
                        && isOk(pattern, entity, Question::getText)
                        && isOk(pattern, entity, Question::getImage)
                )
                .sorted(Comparator.comparingLong(Question::getId));
    }

}
