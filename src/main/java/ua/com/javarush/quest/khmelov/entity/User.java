package ua.com.javarush.quest.khmelov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Builder(builderMethodName = "with")

@Entity
@Table(name = "t_user")
public final class User extends AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;

    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @Transient
    final Collection<Game> games = new ArrayList<>(); //as user

    @Transient
    final Collection<Quest> quests = new ArrayList<>(); //as author (admin)

    @JsonIgnore
    public String getImage() {
        return "image-" + id;
    }

}
