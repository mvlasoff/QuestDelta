package ua.com.javarush.quest.khmelov.repository.impl;

import ua.com.javarush.quest.khmelov.entity.Game;
import ua.com.javarush.quest.khmelov.repository.BaseRepository;
import ua.com.javarush.quest.khmelov.repository.SessionCreator;

public class GameRepository extends BaseRepository<Game> {

    public GameRepository(SessionCreator sessionCreator) {
        super(sessionCreator, Game.class);
    }
}
