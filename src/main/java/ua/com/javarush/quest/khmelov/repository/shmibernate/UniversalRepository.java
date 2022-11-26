package ua.com.javarush.quest.khmelov.repository.shmibernate;

import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.entity.AbstractEntity;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Repository;
import ua.com.javarush.quest.khmelov.repository.dao_jdbc.CnnPool;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class UniversalRepository<T extends AbstractEntity> implements Repository<T> {


    private final Class<T> type;
    private final Dialect dialect;

    //fieldsWithFirstId
    private final List<Field> fields;
    private final String tableName;

    public UniversalRepository(Class<T> type, Dialect dialect) {
        this.type = type;
        this.dialect = dialect;
        if (type.isAnnotationPresent(Entity.class)) {
            fields = Arrays.stream(type.getDeclaredFields())
                    .sorted(Comparator.comparingInt(f -> (f.isAnnotationPresent(Id.class) ? 0 : 1)))
                    .filter(f -> !f.isAnnotationPresent(Transient.class))
                    .toList();
            tableName = StrategyNaming.getTableName(type);
            createTableIfNotExists();
        } else {
            throw new RuntimeException("incorrect class");
        }
    }


    @SneakyThrows
    @Override
    public Stream<T> getAll() {
        String sql = dialect.getGetAllSql(tableName, fields);
        System.out.println(sql);
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> list = new ArrayList<>();
            while (resultSet.next()) {
                T entity = type.getConstructor().newInstance();
                for (Field field : fields) {
                    String valueString = resultSet.getString(StrategyNaming.getFieldName(field));
                    dialect.read(entity, field, valueString);
                }
                list.add(entity);
            }
            return list.stream();
        }
    }

    @Override
    public Stream<T> find(T entity) {
        return null;
    }

    @Override
    @SneakyThrows
    public T get(long id) {
        String sql = dialect.getGetByIdSql(tableName, fields);
        System.out.println(sql);
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            T entity = type.getConstructor().newInstance();
            entity.setId(id);
            setId(entity, preparedStatement, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (int i = 1; i < fields.size(); i++) {
                    Field field = fields.get(i);
                    String valueString = resultSet.getString(StrategyNaming.getFieldName(field));
                    dialect.read(entity, field, valueString);
                }
                return entity;
            }
            return null;
        }
    }

    @Override
    @SneakyThrows
    public void create(T entity) {
        String sql = dialect.getCreateSql(tableName, fields);
        System.out.println(sql);
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            fill(entity, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                long id = resultSet.getLong(1);
                entity.setId(id);
            } else {
                throw new RuntimeException("error save " + entity);
            }
        }
    }


    @SneakyThrows
    @Override
    public void update(T entity) {
        String sql = dialect.getUpdateSql(tableName, fields);
        System.out.println(sql);
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            fill(entity, preparedStatement);
            setId(entity, preparedStatement, fields.size());
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void delete(T entity) {
        String sql = dialect.getDeleteSql(tableName, fields);
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            setId(entity, preparedStatement, 1);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    private void createTableIfNotExists() {
        String sql = dialect.getCreateTableSql(tableName, fields);
        System.out.println(sql);
        try (Connection connection = CnnPool.get();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }


    private Object getValue(T entity, Field field) {
        field.setAccessible(true);
        try {
            Object value = field.get(entity);
            if (field.getType().isEnum()) {
                value = value.toString();
            }
            return value;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    private void fill(T entity, PreparedStatement preparedStatement) {
        for (int i = 1; i < fields.size(); i++) {
            Field field = fields.get(i);
            try {
                Object value = getValue(entity, field);
                preparedStatement.setObject(i, value);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setId(T entity, PreparedStatement preparedStatement, int pos) {
        try {
            Field idField = fields.get(0);
            Object value = getValue(entity, idField);
            preparedStatement.setObject(pos, value);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        UniversalRepository<User> userUniversalRepository = new UniversalRepository<>(User.class, new PostgresDialect());
        User user = User.with().login("L3").password("P").role(Role.GUEST).build();
        userUniversalRepository.create(user);
        User user1 = userUniversalRepository.get(user.getId());
        System.out.println(user1);
        user1.setLogin("L333");
        userUniversalRepository.update(user1);
        userUniversalRepository.delete(user1);
        userUniversalRepository.getAll().forEach(System.out::println);
    }
}
