package ua.com.javarush.quest.khmelov.repository.dao_jdbc.shmibernate;

import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.entity.AbstractEntity;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Repository;
import ua.com.javarush.quest.khmelov.repository.dao_jdbc.CnnPool;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UniversalRepository<T extends AbstractEntity> implements Repository<T> {

    public static final String DELIMITER = ",\n";
    public static final String PREFIX = "(\n";
    public static final String SUFFIX = "\n)";
    public static final String COMMA = ",";
    public static final String PRIMARY_KEY = "BIGSERIAL PRIMARY KEY";
    public static final String NOT_NULL = "NOT NULL ";
    public static final String UNIQUE = "UNIQUE ";
    public static final String SPACE = " ";
    private final Field fieldId;
    private final List<Field> dataFields;
    private final List<Field> allFields;
    private final String tableName;
    private final Class<T> type;
    private final Dialect dialect;

    private String createSql;
    private String updateSql;
    private String deleteSql;
    private String getSql;
    private String getAll;

    public UniversalRepository(Class<T> type, Dialect dialect) {
        this.type = type;
        this.dialect = dialect;
        if (!type.isAnnotationPresent(Entity.class)) {
            throw new RuntimeException("incorrect class");
        }
        fieldId = Arrays.stream(type.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst().orElseThrow();
        dataFields = Arrays.stream(type.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> !f.isAnnotationPresent(Transient.class))
                .toList();
        allFields = Arrays.stream(type.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Transient.class))
                .toList();
        tableName = StrategyNaming.getTableName(type);
        createTableIfNotExists();
    }


    @Override
    @SneakyThrows
    public T get(long id) {
        T entity = type.getConstructor().newInstance();
        if (Objects.isNull(getSql)) {
            String fieldNames = dataFields.stream()
                    .map(StrategyNaming::getFieldName)
                    .collect(Collectors.joining(COMMA));
            String where = StrategyNaming.getFieldName(fieldId) + "=?";
            getSql = "SELECT %s \n\tFROM \"%s\"\n\tWHERE %s;"
                    .formatted(fieldNames, tableName, where);
            System.out.println(getSql);
        }
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(getSql)) {
            entity.setId(id);
            setId(entity, preparedStatement, 1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                for (Field field : dataFields) {
                    String valueString = resultSet.getString(StrategyNaming.getFieldName(field));
                    dialect.read(entity, field, valueString);
                }
                return entity;
            }
            return null;
        }
    }

    @Override
    public Stream<T> getAll() {
        return null;
    }

    @Override
    public Stream<T> find(T entity) {
        return null;
    }

    @Override
    @SneakyThrows
    public void create(T entity) {
        if (Objects.isNull(createSql)) {
            String fieldNames = dataFields.stream()
                    .map(StrategyNaming::getFieldName)
                    .collect(Collectors.joining(COMMA));
            String places = dataFields.stream()
                    .map(f -> "?")
                    .collect(Collectors.joining(COMMA));
            createSql = "INSERT INTO \"%s\" (%s) VALUES (%s)"
                    .formatted(tableName, fieldNames, places);
            System.out.println(createSql);
        }
        //INSERT INTO "user"(login, password, role) VALUES (?, ?, ?);
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(createSql, Statement.RETURN_GENERATED_KEYS)) {
            fill(entity, preparedStatement);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                entity.setId(resultSet.getLong(1));
            } else {
                throw new RuntimeException("error save " + entity);
            }
        }
    }


    @SneakyThrows
    @Override
    public void update(T entity) {
        if (Objects.isNull(updateSql)) {
            String setExpression = dataFields.stream()
                    .map(StrategyNaming::getFieldName)
                    .map(" %s=?"::formatted)
                    .collect(Collectors.joining(COMMA));
            String where = StrategyNaming.getFieldName(fieldId) + "=?";
            updateSql = "UPDATE \"%s\"\n\tSET %s \n\tWHERE %s;"
                    .formatted(tableName, setExpression, where);
            System.out.println(updateSql);

        }
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {
            fill(entity, preparedStatement);
            setId(entity, preparedStatement, dataFields.size() + 1);
            preparedStatement.executeUpdate();
        }
    }

    @SneakyThrows
    @Override
    public void delete(T entity) {
        if (Objects.isNull(deleteSql)) {
            String where = StrategyNaming.getFieldName(fieldId) + "=?";
            deleteSql = "DELETE FROM \"%s\"\n\tWHERE %s;"
                    .formatted(tableName, where);
            System.out.println(deleteSql);
        }
        try (Connection connection = CnnPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(deleteSql)) {
            setId(entity, preparedStatement, 1);
            preparedStatement.executeUpdate();
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
    }

    @SneakyThrows
    private void createTableIfNotExists() {
        String fieldData = allFields.stream()
                .map(this::getOneType)
                .collect(Collectors.joining(DELIMITER, PREFIX, SUFFIX));
        String sql = "CREATE TABLE IF NOT EXISTS %s %s;".formatted(tableName, fieldData);
        System.out.println(sql);
        try (Connection connection = CnnPool.get();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    private String getOneType(Field field) {
        String fieldName = StrategyNaming.getFieldName(field);
        String typeField = field.isAnnotationPresent(Id.class)
                ? PRIMARY_KEY
                : dialect.getDatabaseType(field);
        String constraint = "";
        if (field.isAnnotationPresent(Column.class)) {
            Column column = field.getAnnotation(Column.class);
            constraint = !column.nullable() ? NOT_NULL : SPACE +
                    (column.unique() ? UNIQUE : SPACE);
        }
        String lineFormat = "\t%-20s %-24s %s";
        return lineFormat.formatted(fieldName, typeField, constraint);
    }


    private void fill(T entity, PreparedStatement preparedStatement) {
        for (int i = 0; i < dataFields.size(); i++) {
            Field field = dataFields.get(i);
            try {
                Object value = getValue(entity, field);
                preparedStatement.setObject(i + 1, value);
            } catch (SQLException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void setId(T entity, PreparedStatement preparedStatement, int pos) {
        try {
            Object value = getValue(entity, fieldId);
            preparedStatement.setObject(pos, value);
        } catch (SQLException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private Object getValue(T entity, Field field) throws IllegalAccessException {
        field.setAccessible(true);
        Object value = field.get(entity);
        if (field.getType().isEnum()) {
            value = value.toString();
        }
        return value;
    }
}
