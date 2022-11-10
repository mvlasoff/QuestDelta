package ua.com.javarush.quest.khmelov.repository.memory;

import ua.com.javarush.quest.khmelov.entity.Game;
import ua.com.javarush.quest.khmelov.repository.Repository;

import java.util.Comparator;
import java.util.stream.Stream;

public class GameRepository extends AbstractRepository<Game> implements Repository<Game> {

    @Override
    public Stream<Game> find(Game pattern) {
        return getAll()
                .filter(entity -> isOk(pattern, entity, Game::getId)
                        && isOk(pattern, entity, Game::getUserId)
                        && isOk(pattern, entity, Game::getQuestId)
                        && isOk(pattern, entity, Game::getCurrentQuestionId)
                        && isOk(pattern, entity, Game::getGameState)
                )
                .sorted(Comparator.comparing(Game::getGameState));
    }

}
