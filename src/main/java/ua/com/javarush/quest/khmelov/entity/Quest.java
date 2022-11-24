package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
public class Quest extends AbstractEntity {
    @Id
    Long id;
    Long authorId;
    String name;
    String text;
    Long startQuestionId;
    @Transient
    final Collection<Question> questions = new ArrayList<>();
}
