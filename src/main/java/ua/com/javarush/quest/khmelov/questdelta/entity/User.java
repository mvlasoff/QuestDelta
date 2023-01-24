package ua.com.javarush.quest.khmelov.questdelta.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user", schema = "questdelta")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Transient
    private final GameStatistics gameStatistics;

    public User() {
        gameStatistics = new GameStatistics();
    }

    public User(String login, String password, Role role, GameStatistics gameStatistics) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.gameStatistics = gameStatistics;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    @SuppressWarnings("unused")
    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }
}
