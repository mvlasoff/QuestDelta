package ua.com.javarush.quest.khmelov.repository;

import ua.com.javarush.quest.khmelov.entity.GameStatus;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

public class GameStatusRepository extends AbstractRepository<GameStatus> implements Repository<GameStatus> {

    public static final GameStatusRepository userRepository = new GameStatusRepository();

    public static GameStatusRepository get() {
        return userRepository;
    }

    private GameStatusRepository() {
    }


    @Override
    public Stream<GameStatus> find(GameStatus pattern) {
        return map.values().stream()
                .filter(entity -> isOk(pattern, entity, GameStatus::getId)
                        && isOk(pattern, entity, GameStatus::getUserId)
                        && isOk(pattern, entity, GameStatus::getStartTime)
                        && isOk(pattern, entity, GameStatus::getQuestId)
                        && isOk(pattern, entity, GameStatus::getCurrentQuestionId)
                        && isOk(pattern, entity, GameStatus::getGameState)
                )
                .sorted(Comparator.comparing(GameStatus::getGameState));
    }

}
