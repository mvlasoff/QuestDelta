package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
public class Answer extends AbstractEntity {
    @Id
    Long id;
    Long questionId;
    String text;
    Long nextQuestionId;
}
