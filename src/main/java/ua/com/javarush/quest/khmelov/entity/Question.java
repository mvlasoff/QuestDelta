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
@Table(name = "t_question")
public class Question implements AbstractEntity {
    @Id
    Long id;

    @ManyToOne
    @JoinColumn(name = "quest_id")
    Quest quest;

    String text;

    @Transient
    final Collection<Answer> answers = new ArrayList<>();

    @Transient
    GameState state;

    @JsonIgnore
    public String getImage() {
        return "image-" + id;
    }
}
