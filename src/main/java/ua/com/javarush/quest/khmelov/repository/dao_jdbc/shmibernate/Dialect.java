package ua.com.javarush.quest.khmelov.repository.dao_jdbc.shmibernate;

import ua.com.javarush.quest.khmelov.entity.AbstractEntity;

import java.lang.reflect.Field;

public interface Dialect {

    String getDatabaseType(Field field);

    <T extends AbstractEntity> void read(T entity, Field field, String valueString);
}
