package ua.com.javarush.quest.khmelov.repository;

import ua.com.javarush.quest.khmelov.entity.AbstractEntity;

import java.util.stream.Stream;

public interface Repository<T extends AbstractEntity> {

    Stream<T> getAll();

    Stream<T> find(T entity);

    T get(long id);

    void create(T entity);

    void update(T entity);

    void delete(T entity);
}
