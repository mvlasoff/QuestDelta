package ua.com.javarush.quest.khmelov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString(exclude = "quest")

@Entity
@Table(name = "t_user")
@EqualsAndHashCode(exclude = {"games", "quest"})
public final class User implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String login;

    String password;

    @Enumerated(EnumType.STRING)
    Role role;

    @OneToMany(mappedBy = "user")
    Collection<Quest> quests;

    @Transient
    Collection<Game> games;


    @JsonIgnore
    public String getImage() {
        return "image-" + id;
    }

}



