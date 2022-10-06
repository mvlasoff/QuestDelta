package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.khmelov.entity.GameState;

@Data
@Builder(builderMethodName = "with")
public class GameDto {
    Long id;
    Long questId;
    Long userId;
    QuestionDto question;
    GameState gameState;
}
