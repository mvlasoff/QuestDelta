package ua.com.javarush.quest.khmelov.repository.shmibernate;

import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.entity.AbstractEntity;
import ua.com.javarush.quest.khmelov.repository.Repository;
import ua.com.javarush.quest.khmelov.repository.shmibernate.engine.PostgresDialect;
import ua.com.javarush.quest.khmelov.repository.shmibernate.engine.UniversalRepository;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class PostgresRepository {

    private static final Map<Object, Object> repositories = new HashMap<>();

    <T extends AbstractEntity> Repository<T> get(Class<T> type) {
        //noinspection unchecked
        return (Repository<T>) repositories
                .computeIfAbsent(type, t -> new UniversalRepository<>(type, new PostgresDialect()));
    }

}
