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
    Long id;

    @Column(name = "quest_id")
    Long questId;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "current_question_id")
    Long currentQuestionId;

    @Enumerated(EnumType.STRING)
    @Column(name = "game_state")

    GameState gameState;

}
