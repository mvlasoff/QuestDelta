package ua.com.javarush.quest.khmelov.repository.dao_jdbc.init;

import ua.com.javarush.quest.khmelov.repository.dao_jdbc.CnnPool;
import ua.com.javarush.quest.khmelov.repository.dao_jdbc.DaoException;

import java.sql.*;

@SuppressWarnings("SameParameterValue")
public class JdbcStarter {

//    public static final String DB_URL = "jdbc:postgresql://localhost:5432/game";
//    public static final String DB_USER = "postgres";
//    public static final String DB_PASSWORD = "postgre";

    public static void main(String[] args) {
        try {
            execute(SqlData.SQL_DELETE_TABLE_USER);
            execute(SqlData.SQL_CREATE_TABLE_USER);
            executeUpdate(SqlData.SQL_ADD_USERS);
            printAllUsers(SqlData.SQL_ALL_USERS);
        } finally {
            CnnPool.destroy();
        }

    }

    private static void printAllUsers(String sqlAllUsers) {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery(sqlAllUsers);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                System.out.printf("%-10s", columnName);
            }
            System.out.println("\n" + "-".repeat(10 * columnCount));
            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                System.out.printf("%-10d %-10s %-10s %-10s%n",
                        id, login, password, role);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    private static void execute(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            boolean dataFound = statement.execute(sql);
            if (dataFound) {
                throw new DaoException("incorrect operation sql=" + sql);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static void executeUpdate(String sql) {
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return CnnPool.get();
    }
}
