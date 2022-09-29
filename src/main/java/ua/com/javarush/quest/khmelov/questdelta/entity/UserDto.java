package ua.com.javarush.quest.khmelov.questdelta.entity;

public class UserDto {
    private String name;

    private Role role;


    public UserDto(User user) {
        this.name = user.getLogin();
        this.role = user.getRole();
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }
}
