package ua.com.javarush.quest.khmelov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Question extends AbstractEntity{
    Long id;
    Long questId;
    String image;
    String text;
    Collection<Answer> answers;
    Long correctQuestionId;
    Long incorrectQuestionId;
}
