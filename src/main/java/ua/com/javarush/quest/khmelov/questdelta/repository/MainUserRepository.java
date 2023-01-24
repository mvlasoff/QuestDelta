package ua.com.javarush.quest.khmelov.questdelta.repository;

import ua.com.javarush.quest.khmelov.questdelta.entity.Role;

import java.util.Optional;

public abstract class MainUserRepository<T> extends MainRepository<T> {
    public abstract Optional<T> find(String login, String password);

    public abstract Optional<T> verify(String login);

    public abstract void create(String login, String password, Role role);
}
