package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
@Table(name = "t_answer")
public class Answer implements AbstractEntity {
    @Id
    Long id;
    Long questionId;
    String text;
    Long nextQuestionId;
}
