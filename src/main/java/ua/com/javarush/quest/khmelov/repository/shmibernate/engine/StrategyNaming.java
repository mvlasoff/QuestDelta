package ua.com.javarush.quest.khmelov.repository.shmibernate.engine;

import lombok.experimental.UtilityClass;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class StrategyNaming {

    private final Map<String, String> cache = new HashMap<>();

    String getFieldName(Field field) {
        return field.isAnnotationPresent(Column.class)
                ? field.getAnnotation(Column.class).name()
                : convertToSnakeStyle(field.getName());
    }

    static String getTableName(Class<?> type) {
        return type.isAnnotationPresent(Table.class)
                ? type.getAnnotation(Table.class).name()
                : "t_" + convertToSnakeStyle(type.getSimpleName());
    }

    private String convertToSnakeStyle(String camelCase) {
        return cache.computeIfAbsent(camelCase, c -> c
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase());
    }
}
