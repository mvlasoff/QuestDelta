package ua.com.javarush.quest.khmelov.repository.dao;

import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.config.Config;
import ua.com.javarush.quest.khmelov.exception.AppException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@UtilityClass
public class ConnectionCreator {

    private static final String URI;
    private static final String USER;
    private static final String PASSWORD;

    static {
        Config.DataBase dataBase = Config.get().dataBase;
        URI = dataBase.getUri();
        USER = dataBase.getUser();
        PASSWORD = dataBase.getPassword();
    }

    Connection get() {
        try {
            return DriverManager.getConnection(URI, USER, PASSWORD);
        } catch (SQLException e) {
            throw new AppException("Connection failed", e);
        }
    }
}

