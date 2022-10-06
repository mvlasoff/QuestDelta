package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Game extends Entity {
    Long id;
    Long questId;
    Long userId;
    Long currentQuestionId;
    GameState gameState;
}
