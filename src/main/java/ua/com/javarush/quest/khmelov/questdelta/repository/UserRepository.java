package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepository {
    private static final UserRepository userRepository = new UserRepository();
    private final Map<Long, User> users;

    private AtomicLong id = new AtomicLong(0);

    private UserRepository() {
        users = new HashMap<>();
        users.put(id.getAndIncrement(), new User("admin", "admin", Role.ADMIN, new GameStatistics()));
        users.put(id.getAndIncrement(), new User("user", "12345", Role.USER, new GameStatistics()));
        users.put(id.getAndIncrement(), new User("guest", "00000", Role.GUEST, new GameStatistics()));
    }

    public static UserRepository get() {
        return userRepository;
    }

    public Map<Long, User> getAll() {
        return users;
    }

    public Optional<User> get(long userId) {
        return Optional.ofNullable(users.get(userId));
    }

    public Optional<User> find(String login, String password) {
        return users.values().stream()
                .filter(user -> user.getLogin().equals(login))
                .filter(user -> user.getPassword().equals(password))
                .findFirst();
    }

    public Optional<User> verify(String login) {
        return users.values().stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst();
    }

    public void doPost(String login, String password, Role role) {
        users.put(id.getAndIncrement(), new User(login, password, role, new GameStatistics()));
    }
}
