package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.persistence.Column;
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
    private Long id;

    @Column(name="question_id")
    private Long questionId;

    private String text;

    @Column(name="next_question_id")
    private Long nextQuestionId;
}
