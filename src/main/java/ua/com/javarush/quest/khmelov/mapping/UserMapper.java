package ua.com.javarush.quest.khmelov.mapping;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.User;

import java.util.Optional;

/**
 * Class package-private. Use only <code>interface Mapper</code>
 */
class UserMapper implements Mapper<User, UserDto> {

    @Override
    public Optional<UserDto> write(User user) {
        return user != null
                ? Optional.of(UserDto.with()
                .id(user.getId())
                .login(user.getLogin())
                .image(user.getImage())
                .role(user.getRole())
                .build()
        ) : Optional.empty();
    }

    @Override
    public User read(FormData formData) {
        User user = User.with().build();
        return fill(user, formData);
    }
}
