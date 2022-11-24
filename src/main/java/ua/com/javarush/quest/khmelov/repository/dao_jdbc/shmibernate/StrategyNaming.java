package ua.com.javarush.quest.khmelov.repository.dao_jdbc.shmibernate;

import lombok.experimental.UtilityClass;

import javax.persistence.Column;
import javax.persistence.Table;
import java.lang.reflect.Field;

@UtilityClass
public class StrategyNaming {
    String getFieldName(Field field) {
        return field.isAnnotationPresent(Column.class)
                ? field.getAnnotation(Column.class).name()
                : "f_" + field.getName().toLowerCase();
    }

    static String getTableName(Class<?> type) {
        return type.isAnnotationPresent(Table.class)
                ? type.getAnnotation(Table.class).name()
                : "t_" + type.getSimpleName().toLowerCase();
    }
}
