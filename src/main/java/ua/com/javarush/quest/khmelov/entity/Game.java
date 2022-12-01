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
@Table(name = "t_game")
public class Game implements AbstractEntity {
    @Id
    Long id;
    Long questId;
    Long userId;
    Long currentQuestionId;
    GameState gameState;
}
