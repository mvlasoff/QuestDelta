package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
@Table(name = "t_game")
public class Game implements AbstractEntity {
    @Id
    private Long id;

    @Column(name = "quest_id")
    private Long questId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "current_question_id")
    private Long currentQuestionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_state")
    private GameState gameState;

}
