package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.GameDto;
import ua.com.javarush.quest.khmelov.entity.Game;

import java.util.Optional;

/**
 * Class package-private. Use only <code>interface Mapper</code>
 */
class GameMapper implements Mapper<Game, GameDto> {

    @Override
    public Optional<GameDto> get(Game game) {
        return game != null
                ? Optional.of(GameDto.with()
                .id(game.getId())
                .questId(game.getUserId())
                .gameState(game.getGameState())
                .userId(game.getUserId())
                .build()
        ) : Optional.empty();
    }

    @Override
    public Game parse(FormData formData) {
        Game quest = Game.with().build();
        return fill(quest, formData);
    }
}
