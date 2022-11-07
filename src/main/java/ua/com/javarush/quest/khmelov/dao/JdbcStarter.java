package ua.com.javarush.quest.khmelov.dao;

import java.sql.*;

public class JdbcStarter {

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/game";
    public static final String DB_USER = "postgres";
    public static final String DB_PASSWORD = "postgre";

    public static void main(String[] args) {
        execute(SqlData.sqlDeleteTableUser);
        execute(SqlData.sqlCreateTableUser);
        executeUpdate(SqlData.sqlAddUsers);
        printAllUsers(SqlData.sqlAllUsers);
    }

    private static void printAllUsers(String sqlAllUsers) {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement()
        ){
            ResultSet resultSet = statement.executeQuery(sqlAllUsers);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                System.out.printf("%-10s",columnName);
            }
            System.out.println("\n"+"-".repeat(10*columnCount));
            while (resultSet.next()){
                long id = resultSet.getLong("id");
                String login = resultSet.getString("login");
                String password = resultSet.getString("password");
                String role = resultSet.getString("role");
                System.out.printf("%-10d %-10s %-10s %-10s%n",
                        id,login,password,role);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }

    private static void execute(String sql) {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ) {
            boolean dataFound = statement.execute(sql);
            if (dataFound){
                throw new DaoException("incorrect operation sql="+sql);
            };
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    private static boolean executeUpdate(String sql) {
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ) {
            return 0<statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_URL,
                DB_USER,
                DB_PASSWORD);
    }
}
