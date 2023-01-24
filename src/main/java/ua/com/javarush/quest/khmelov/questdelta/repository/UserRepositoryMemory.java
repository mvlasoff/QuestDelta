package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class UserRepositoryMemory extends MainUserRepository<User> {
    private static final MainUserRepository<User> USER_REPOSITORY_MEMORY = new UserRepositoryMemory();

    private final Map<Long, User> users;

    private final AtomicLong id = new AtomicLong(0);

    private UserRepositoryMemory() {
        users = new HashMap<>();
        users.put(id.getAndIncrement(), new User("admin", "admin", Role.ADMIN, new GameStatistics()));
        users.put(id.getAndIncrement(), new User("user", "12345", Role.USER, new GameStatistics()));
        users.put(id.getAndIncrement(), new User("guest", "00000", Role.GUEST, new GameStatistics()));
    }

    public static MainUserRepository<User> get() {
        return USER_REPOSITORY_MEMORY;
    }

    public Collection<User> getAll() {
        return users.values();
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

    public void create(String login, String password, Role role) {
        users.put(id.getAndIncrement(), new User(login, password, role, new GameStatistics()));
    }
}
