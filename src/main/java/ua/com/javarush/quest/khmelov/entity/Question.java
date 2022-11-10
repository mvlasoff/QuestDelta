package ua.com.javarush.quest.khmelov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public class Question extends AbstractEntity {
    Long id;
    Long questId;
    @JsonIgnore
    public String getImage() {
        return "image-" + id;
    }
    String text;
    final Collection<Answer> answers = new ArrayList<>();
    GameState state;
}
