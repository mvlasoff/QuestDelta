package ua.com.javarush.quest.khmelov.questdelta.repository;

import java.util.Collection;
import java.util.Optional;

public abstract class MainRepository<T> {

    public abstract Optional<T> get(long id);

    public abstract Collection<T> getAll();
}
