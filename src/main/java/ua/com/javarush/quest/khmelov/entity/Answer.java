package ua.com.javarush.quest.khmelov.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
@RequiredArgsConstructor
@Table(name = "t_answer")
public class Answer implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="question_id")
    private Long questionId;

    private String text;

    @Column(name="next_question_id")
    private Long nextQuestionId;

}
