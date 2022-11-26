package ua.com.javarush.quest.khmelov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
public class Question extends AbstractEntity {
    @Id
    Long id;
    Long questId;
    String text;
    @Transient
    final Collection<Answer> answers = new ArrayList<>();
    GameState state;

    @JsonIgnore
    public String getImage() {
        return "image-" + id;
    }
}
