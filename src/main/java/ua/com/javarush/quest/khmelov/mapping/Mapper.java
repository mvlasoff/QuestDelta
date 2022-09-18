package ua.com.javarush.quest.khmelov.mapping;

import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.Entity;
import ua.com.javarush.quest.khmelov.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

/**
 * Mapper Entity -> DTO and fill from Request -> Entity
 */
public interface Mapper<E extends Entity, R> {

    /**
     * form Service to Servlet layer
     * @param entity from DB
     * @return DTO
     */
    Optional<R> write(E entity);

    /**
     * Data form Servlet convert to new Entity
     * @param formData wrapper for HTTP-request
     * @return entity for DB
     */
    E read(FormData formData);

    //all mappers
    Mapper<User, UserDto> user = new UserMapper();

    /**
     * Data form Servlet convert to existing instance Entity
     * demo fill with Reflection API (not easy)
     * @param formData wrapper for HTTP-request
     * @return existing entity
     */
    default E fill(E entity, FormData formData) {
        Class<? extends Entity> aClass = entity.getClass();
        Method[] methods = aClass.getMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("set")
                    && Modifier.isPublic(method.getModifiers())
                    && method.getParameterCount() == 1
            ) {
                String name = methodName.substring(3, 4).toLowerCase() + methodName.substring(4);
                String value = formData.getParameter(name);
                if (Objects.nonNull(value)) {
                    Class<?> type = method.getParameterTypes()[0];
                    if (InnerMapForPrimitiveData.map.containsKey(type)) {
                        Object o = InnerMapForPrimitiveData.map.get(type).apply(value);
                        set(entity, aClass, name, type, o);
                    } else if (type.isEnum()) {
                        for (Object enumConstant : type.getEnumConstants()) {
                            if (enumConstant.toString().equalsIgnoreCase(value)) {
                                set(entity, aClass, name, type, enumConstant);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return entity;
    }

    @UtilityClass
    class InnerMapForPrimitiveData {
        private static final Map<Class<?>, Function<String, Object>> map = Map.of(
                int.class, Integer::parseInt,
                long.class, Long::parseLong,
                double.class, Double::parseDouble,
                Integer.class, Integer::valueOf,
                Long.class, Long::valueOf,
                Double.class, Double::valueOf,
                String.class, String::toString
        );
    }

    private static void set(Object entity, Class<? extends Entity> aClass, String name, Class<?> type, Object o) {
        try {
            String setter = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
            aClass.getMethod(setter, type).invoke(entity, o);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

