package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Answer extends Entity {
    Long id;
    Long questionId;
    String text;
    Long nextQuestionId;
}
