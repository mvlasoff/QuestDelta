package ua.com.javarush.quest.khmelov.config;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class Winter {
    //need annotation @Component or other for scan package and auto build context
    private final Map<Class<?>, Object> context = new HashMap<>();

    @SuppressWarnings("unchecked")
    public <T> T getBeen(Class<T> type) {
        try {
            if (context.containsKey(type)) {
                return (T) context.get(type);
            } else {
                Constructor<?>[] constructors = type.getConstructors();
                Constructor<?> constructor = constructors[0];
                Class<?>[] parameterTypes = constructor.getParameterTypes();
                Object[] parameters = new Object[parameterTypes.length];
                for (int i = 0; i < parameters.length; i++) {
                    parameters[i] = getBeen(parameterTypes[i]);
                }
                Object component = constructor.newInstance(parameters);
                context.put(type, component);
                return (T) component;
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Context broken for " + type, e);
        }
    }
}
