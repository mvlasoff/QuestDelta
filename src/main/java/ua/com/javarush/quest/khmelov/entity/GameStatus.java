package ua.com.javarush.quest.khmelov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.ZonedDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class GameStatus extends AbstractEntity {
    Long id;
    Long questId;
    Long userId;
    Long currentQuestionId;
    ZonedDateTime startTime;
    GameState gameState;
}
