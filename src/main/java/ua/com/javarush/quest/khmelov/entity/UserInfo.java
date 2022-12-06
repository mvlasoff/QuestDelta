package ua.com.javarush.quest.khmelov.entity;

import lombok.*;

import javax.annotation.MatchesPattern;
import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "with")

@Getter
@Setter
@ToString

@Entity
@Table(name = "t_user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String address;

    @MatchesPattern(value = "+[0-9()-\\s]+")
    String phone;

    @OneToOne
    @ToString.Exclude
    User user;
}
