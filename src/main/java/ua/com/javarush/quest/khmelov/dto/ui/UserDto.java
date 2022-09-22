package ua.com.javarush.quest.khmelov.dto.ui;

import lombok.Builder;
import lombok.Data;
import ua.com.javarush.quest.khmelov.entity.Role;

@Data
@Builder(builderMethodName = "with")
public class UserDto {

    Long id;

    String login;

    String image;

    Role role;

}