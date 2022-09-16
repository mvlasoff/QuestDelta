package ua.com.javarush.quest.khmelov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collection;

@Data
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")
public final class User extends AbstractEntity{

    Long id;

    String login;

    String password;

    String image;

    Role role;

    Collection<GameStatus> gameStatuses; //as user

    Collection<Quest> quests; //as author (admin)

}
