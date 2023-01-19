package ua.com.javarush.quest.khmelov.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;


@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString
@Entity
@Table(name = "t_user")
public final class User implements AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private final Collection<Quest> quests = new ArrayList<>();

    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private Collection<Game> games = new ArrayList<>();

//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private UserInfo userInfo;

    @ManyToMany
    @JoinTable(name = "t_game",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "quest_id", referencedColumnName = "id"))
    @ToString.Exclude
    @LazyCollection(value = LazyCollectionOption.TRUE)
    private final Collection<Quest> questsInGame = new ArrayList<>();

    //---------------------------- end  entity ------------------------------------------
    @JsonIgnore
    public String getImage() {
        return "user-" + id;
    }

}



