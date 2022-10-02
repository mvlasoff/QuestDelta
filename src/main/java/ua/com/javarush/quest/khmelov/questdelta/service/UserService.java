package ua.com.javarush.quest.khmelov.questdelta.service;

import ua.com.javarush.quest.khmelov.questdelta.entity.Role;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;
import ua.com.javarush.quest.khmelov.questdelta.repository.UserRepository;

import java.util.Map;
import java.util.Optional;

public class UserService {
    private final UserRepository userRepository;
    private static UserService userService;

    private UserService() {
        userRepository = UserRepository.get();
    }

    public static UserService getUserService() {
        if (userService == null) {
            userService = new UserService();
        }
        return userService;
    }

    public Map<Long, User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long userId) {
        return userRepository.get(userId);
    }

    public Optional<User> find(String login, String password) {
        Optional<User> user = userRepository.find(login, password);
        return user;
    }

    public Optional<User> verify(String login) {
        Optional<User> user = userRepository.verify(login);
        return user;
    }

    public void doPost(String login, String password, Role role) {
        userRepository.doPost(login, password, role);
    }
}
