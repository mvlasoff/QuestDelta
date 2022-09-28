package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class AnswerDto {
    Long id;
    String text;
    Long nextQuestionId;
}
