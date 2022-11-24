package ua.com.javarush.quest.khmelov.repository.shmibernate;

import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.entity.AbstractEntity;

import javax.persistence.Enumerated;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.UnknownFormatConversionException;
import java.util.function.Function;


public class PostgresDialect implements Dialect{

    private static final Map<Class<?>, String> dbType = Map.of(
            int.class, "INT",
            Integer.class, "INT",
            long.class, "INT8",
            Long.class, "INT8",
            double.class, "FLOAT8",
            Double.class, "FLOAT8",
            String.class, "TEXT"
            //..................... и т.д.
    );

    private static final Map<Class<?>, Function<String, Object>> readMap = Map.of(
            int.class, Integer::parseInt,
            Integer.class, Integer::valueOf,
            long.class, Long::parseLong,
            Long.class, Long::valueOf,
            double.class, Double::parseDouble,
            Double.class, Double::valueOf,
            String.class, String::toString
            //..................... и т.д.
    );

    @SneakyThrows
    public <T extends AbstractEntity> void read(T entity, Field field, String valueString) {
        field.setAccessible(true);
        Class<?> type = field.getType();
        Object value = null;
        if (!type.isEnum()) {
            value = readMap.get(type).apply(valueString);
        } else {
            for (Object enumConstant : type.getEnumConstants()) {
                if (enumConstant.toString().equalsIgnoreCase(valueString)) {
                    value = enumConstant;
                    break;
                }
            }
        }
        field.set(entity, value);
    }

    public String getDatabaseType(Field field) {
        if (field.isAnnotationPresent(Enumerated.class)) {
            return "VARCHAR(255)"; //enum
        }
        Class<?> aClass = field.getType();
        String type = dbType.get(aClass);
        if (Objects.isNull(type)) {
            throw new UnknownFormatConversionException(aClass.getName());
        }
        return type;
    }

}
