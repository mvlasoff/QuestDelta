package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import java.util.Collection;


@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")
public final class User extends Entity {

    Long id;

    String login;

    String password;

    public String getImage() {
        return "image-" + id;
    }

    Role role;

    Collection<GameStatus> gameStatuses; //as user

    Collection<Quest> quests; //as author (admin)

}
