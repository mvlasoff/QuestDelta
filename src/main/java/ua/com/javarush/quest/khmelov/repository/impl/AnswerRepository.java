package ua.com.javarush.quest.khmelov.repository.impl;

import ua.com.javarush.quest.khmelov.entity.Answer;
import ua.com.javarush.quest.khmelov.repository.BaseRepository;
import ua.com.javarush.quest.khmelov.repository.SessionCreator;

public class AnswerRepository extends BaseRepository<Answer> {

    public AnswerRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Answer.class);
    }
}
