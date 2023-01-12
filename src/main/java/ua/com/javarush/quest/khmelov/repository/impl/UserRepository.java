package ua.com.javarush.quest.khmelov.repository.impl;

import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.BaseRepository;
import ua.com.javarush.quest.khmelov.repository.SessionCreator;

public class UserRepository extends BaseRepository<User> {


    public UserRepository(SessionCreator sessionCreator) {
        super(sessionCreator, User.class);
    }


}
