package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString(exclude = "user")

@Entity
@Table(name = "t_quest")
public class Quest implements AbstractEntity {
    @Id
    Long id;
    @ManyToOne
    User user;
    String name;
    String text;
    Long startQuestionId;

    @Transient
    Collection<Question> questions;
}
