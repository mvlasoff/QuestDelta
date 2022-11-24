package ua.com.javarush.quest.khmelov.repository.dao_jdbc;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.config.Config;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@UtilityClass
public class CnnPool {

    private static final Config.DataBase DATA_BASE = Config.get().dataBase;
    private static final String URI = DATA_BASE.getUri();
    private static final String USER = DATA_BASE.getUser();
    private static final String PASSWORD = DATA_BASE.getPassword();

    public static final int SIZE = 10;
    public static final BlockingQueue<Object> queue = new LinkedBlockingQueue<>(SIZE);
    public static final ArrayList<Connection> connections = new ArrayList<>(SIZE);

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        fill();
    }

    @SneakyThrows
    private void fill() {
        for (int i = 0; i < SIZE; i++) {
            Connection connection = DriverManager.getConnection(URI, USER, PASSWORD);
            Object proxyCnn = Proxy.newProxyInstance(
                    CnnPool.class.getClassLoader(),
                    new Class[]{Connection.class},
                    (proxy, method, args) -> "close".equals(method.getName())
                            ? Boolean.valueOf(queue.add(proxy))
                            : method.invoke(connection, args));
            queue.put(proxyCnn);
            connections.add(connection);
        }
    }

    @SneakyThrows
    public Connection get() {
        return (Connection) queue.take();
    }


    @SneakyThrows
    public void destroy() {
        for (Connection connection : connections) {
            if (!connection.getAutoCommit()) {
                connection.rollback();
                connection.setAutoCommit(true);
            }
            connection.close();
        }
    }
}

