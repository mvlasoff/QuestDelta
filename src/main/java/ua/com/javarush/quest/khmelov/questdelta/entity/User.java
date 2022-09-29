package ua.com.javarush.quest.khmelov.questdelta.entity;

import ua.com.javarush.quest.khmelov.questdelta.repository.GameStatistics;

public class User {
    private String login;
    private String password;

    private Role role;

    private GameStatistics gameStatistics;

    public User(String login, String password, Role role, GameStatistics gameStatistics) {
        this.login = login;
        this.password = password;
        this.role = role;
        this.gameStatistics = gameStatistics;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
