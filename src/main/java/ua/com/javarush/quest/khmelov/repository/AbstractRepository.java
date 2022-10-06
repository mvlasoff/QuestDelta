package ua.com.javarush.quest.khmelov.repository;

import ua.com.javarush.quest.khmelov.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.stream.Stream;

public abstract class AbstractRepository<T extends Entity> implements Repository<T> {

    public static final AtomicLong id = new AtomicLong(10L);
    protected final Map<Long, T> map = new HashMap<>();

    @Override
    public Stream<T> getAll() {
        return map.values().stream();
    }

    @Override
    public abstract Stream<T> find(T pattern);


    protected <V> boolean isOk(T pattern, T current, Function<T, V> fieldGetter) {
        V currentFieldValue = fieldGetter.apply(current);
        V patternFieldValue = fieldGetter.apply(pattern);
        return Objects.isNull(patternFieldValue) || patternFieldValue.equals(currentFieldValue);
    }

    @Override
    public T get(long id) {
        return map.get(id);
    }

    @Override
    public void create(T entity) {
        if (entity.getId() == null || entity.getId() < 1) {
            entity.setId(id.incrementAndGet());
        }
        update(entity);
    }

    @Override
    public void update(T entity) {
        map.put(entity.getId(), entity);
    }

    @Override
    public void delete(T entity) {
        map.remove(entity.getId());
    }
}
