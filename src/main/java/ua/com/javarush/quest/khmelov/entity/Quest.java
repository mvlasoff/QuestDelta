package ua.com.javarush.quest.khmelov.entity;

import lombok.*;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
@Table(name = "t_quest")
@FetchProfile(name = "quest_sub_select_question", fetchOverrides = {
        @FetchProfile.FetchOverride(
                entity = Quest.class,
                association = "questions",
                mode = FetchMode.JOIN
        )
})
public class Quest implements AbstractEntity {
    @Id
    @OrderColumn
    Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    User user;

    @ManyToMany
    @JoinTable(name = "t_game",
            joinColumns = @JoinColumn(name = "quest_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    @ToString.Exclude
    final Collection<User> players=new ArrayList<>();

    String name;

    String text;

    Long startQuestionId;

    @OneToMany(mappedBy = "quest")
    @ToString.Exclude
    @LazyCollection(value = LazyCollectionOption.EXTRA)
    final Collection<Question> questions = new ArrayList<>();
}
