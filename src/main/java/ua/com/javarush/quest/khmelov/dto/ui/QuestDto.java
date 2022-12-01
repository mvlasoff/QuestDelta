package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;

import java.util.Collection;

@Data
@Builder(builderMethodName = "with")
public class QuestDto {
    Long id;
    Long userId;
    String name;
    String text;
    Long startQuestionId;
    Collection<QuestionDto> questions;
}
