package ua.com.javarush.quest.khmelov.repository.liquibase;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.Scope;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.repository.dao_jdbc.CnnPool;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class Validator {

    @SneakyThrows
    public static void main(String[] args) {
        Map<String, Object> config = new HashMap<>();
        Connection connection = CnnPool.get();
        try (connection) {
            Scope.child(config, () -> {
                Database database = DatabaseFactory
                        .getInstance()
                        .findCorrectDatabaseImplementation(new JdbcConnection(connection));

                Liquibase liquibase = new liquibase.Liquibase(
                        "classpath:/liquibase/changelog.xml",
                        new ClassLoaderResourceAccessor(),
                        database
                );
                liquibase.update(new Contexts(), new LabelExpression());
            });
        }
        CnnPool.destroy();
    }
}
