package ua.com.javarush.quest.khmelov.questdelta.service;

import ua.com.javarush.quest.khmelov.questdelta.entity.Role;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;
import ua.com.javarush.quest.khmelov.questdelta.repository.MainUserRepository;
import ua.com.javarush.quest.khmelov.questdelta.repository.UserRepositoryDB;

import java.util.Collection;
import java.util.Optional;

public class UserService {

    //In memory user storage.
    //private final static MainUserRepository<User> userRepository = UserRepositoryMemory.get();

    //Persistent user storage.
    private final static MainUserRepository<User> userRepository = UserRepositoryDB.get();

    private static final UserService userService = new UserService();

    public static UserService getUserService() {
        return userService;
    }

    @SuppressWarnings("unused")
    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> get(long userId) {
        return userRepository.get(userId);
    }

    public Optional<User> find(String login, String password) {
        return userRepository.find(login, password);
    }

    public Optional<User> verify(String login) {
        return userRepository.verify(login);
    }

    public void doPost(String login, String password, Role role) {
        userRepository.create(login, password, role);
    }

    public void update(User user) {
        userRepository.update(user);
    }
}
