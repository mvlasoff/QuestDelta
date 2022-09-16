package ua.com.javarush.quest.khmelov.service;

import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.mapping.UserMapper;
import ua.com.javarush.quest.khmelov.repository.Repository;
import ua.com.javarush.quest.khmelov.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public enum UserService {

    INSTANCE;

    private final Repository<User> userRepository = UserRepository.get();

    public void create(User user) {
        userRepository.create(user);
    }

    public Collection<UserDto> getAll() {
        return userRepository.getAll().stream().map(UserMapper.GET::from).toList();
    }

    public Optional<UserDto> get(long id) {
        User user = userRepository.get(id);
        return Optional.ofNullable(UserMapper.GET.from(user));
    }

    public Collection<User> find(User patternUser) {
        return userRepository.find(patternUser);
    }

    public Optional<UserDto> find(String login, String password) {
        User user = User.with().login(login).password(password).build();
        Collection<User> users = find(user);
        user = (users.size() > 0) ? users.iterator().next() : null;
        return Optional.ofNullable(UserMapper.GET.from(user));
    }

    public boolean update(long id, String image, String login, String role) {
        User user = userRepository.get(id);
        if (user != null) {
            user.setImage(image != null ? image : user.getImage());
            user.setLogin(login);
            user.setRole(Role.valueOf(role));
            userRepository.update(user);
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(long id) {
        User user = userRepository.get(id);
        if (user != null) {
            userRepository.delete(user);
            return true;
        } else {
            return false;
        }
    }
}
