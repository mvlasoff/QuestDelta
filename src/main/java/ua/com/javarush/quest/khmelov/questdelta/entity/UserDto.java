package ua.com.javarush.quest.khmelov.questdelta.entity;

@SuppressWarnings("unused")
public class UserDto {
    private final String name;
    private final Role role;
    private final GameStatistics gameStatistics;


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
