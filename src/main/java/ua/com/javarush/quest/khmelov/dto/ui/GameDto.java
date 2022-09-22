package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import ua.com.javarush.quest.khmelov.entity.GameState;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class GameDto {
    Long id;
    Long questId;
    Long userId;
    QuestionDto question;
    GameState gameState;
}
