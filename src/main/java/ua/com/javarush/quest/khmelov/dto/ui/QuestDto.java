package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.khmelov.entity.Question;

import java.util.ArrayList;
import java.util.Collection;

@Data
@Builder(builderMethodName = "with")
public class QuestDto {
    Long id;
    Long authorId;
    String name;
    String text;
    Long startQuestionId;
    Collection<QuestionDto> questions;
}
