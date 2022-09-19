package ua.com.javarush.quest.khmelov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Answer extends Entity {
    Long id;
    Long questionId;
    String text;
    Long nextQuestionId;
}
