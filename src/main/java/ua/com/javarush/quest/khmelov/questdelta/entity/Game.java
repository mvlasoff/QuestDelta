package ua.com.javarush.quest.khmelov.questdelta.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(schema = "questdelta", name = "game_stat")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "games_count")
    private Integer gamesCount;

    @Column(name = "games_won")
    private Integer gamesWon;

    @ManyToMany
    @JoinTable(name = "user_game_stat",
            joinColumns = @JoinColumn(name = "stat_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> users;


    public Game() {
        this.gamesCount = 0;
        this.gamesWon = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGamesCount() {
        return gamesCount;
    }

    public void setGamesCount(Integer gamesCount) {
        this.gamesCount = gamesCount;
    }

    public Integer getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(Integer gamesWon) {
        this.gamesWon = gamesWon;
    }

    @SuppressWarnings("unused")
    public List<User> getUsers() {
        return users;
    }

    @SuppressWarnings("unused")
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
