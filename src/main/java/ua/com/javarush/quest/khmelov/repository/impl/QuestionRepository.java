package ua.com.javarush.quest.khmelov.repository.impl;

import ua.com.javarush.quest.khmelov.entity.Question;
import ua.com.javarush.quest.khmelov.repository.BaseRepository;
import ua.com.javarush.quest.khmelov.repository.SessionCreator;

public class QuestionRepository extends BaseRepository<Question> {

    public QuestionRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Question.class);
    }
}
