package ua.com.javarush.quest.khmelov.repository;

import lombok.experimental.UtilityClass;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.JdbcDatabaseContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import ua.com.javarush.quest.khmelov.config.Winter;

@UtilityClass
public class Container {

    private final static JdbcDatabaseContainer<?> CONTAINER;

    static {
        //noinspection resource
        CONTAINER = new PostgreSQLContainer<>("postgres:14.6");
        CONTAINER.start();
        Configuration configuration = new Configuration();

        String jdbcUrl = CONTAINER.getJdbcUrl();
        String username = CONTAINER.getUsername();
        String password = CONTAINER.getPassword();

        configuration.setProperty("hibernate.connection.url", jdbcUrl);
        configuration.setProperty("hibernate.connection.username", username);
        configuration.setProperty("hibernate.connection.password", password);
        configuration.configure();

        SessionCreator sessionCreator = new SessionCreator(configuration);
        Winter.setBean(SessionCreator.class, sessionCreator);
    }

    @Test
    public SessionCreator init() {
        return Winter.getBean(SessionCreator.class);
    }


}