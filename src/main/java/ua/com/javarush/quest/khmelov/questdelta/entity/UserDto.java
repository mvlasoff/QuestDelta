package ua.com.javarush.quest.khmelov.questdelta.entity;

import ua.com.javarush.quest.khmelov.questdelta.repository.GameStatistics;

public class UserDto {
    private String name;
    private Role role;
    private GameStatistics gameStatistics;


    public UserDto(User user) {
        this.name = user.getLogin();
        this.role = user.getRole();
        this.gameStatistics = user.getGameStatistics();
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    public GameStatistics getGameStatistics() {
        return gameStatistics;
    }
}
