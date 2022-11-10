package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Quest extends AbstractEntity {
    Long id;
    Long authorId;
    String name;
    String text;
    Long startQuestionId;
    final Collection<Question> questions = new ArrayList<>();
}
