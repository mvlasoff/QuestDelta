package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.User;

public enum UserMapper implements MapperDto<User, UserDto> {

    GET;

    @Override
    public UserDto from(User user) {
        return user == null ? null : UserDto.with()
                .id(user.getId())
                .login(user.getLogin())
                .image(user.getImage())
                .role(user.getRole())
                .build();
    }


}
