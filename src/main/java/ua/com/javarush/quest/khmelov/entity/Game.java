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
public class Game extends AbstractEntity {
    @Id
    Long id;
    Long questId;
    Long userId;
    Long currentQuestionId;
    GameState gameState;
}
