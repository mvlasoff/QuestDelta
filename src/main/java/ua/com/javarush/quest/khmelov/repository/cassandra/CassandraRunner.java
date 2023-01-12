package ua.com.javarush.quest.khmelov.repository.cassandra;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.*;
import com.datastax.oss.driver.api.core.metadata.Node;

import java.net.InetSocketAddress;

public class CassandraRunner {
    public static void main(String[] args) {
        CqlSession session = CqlSession.builder()
                .addContactPoint(new InetSocketAddress("127.0.0.1", 9042))
                .withLocalDatacenter("datacenter1")
                .withKeyspace("quest")
                .build();
        try (session) {
            //ResultSet resultSet = session.execute("SELECT * from t_user");
            SimpleStatementBuilder statementBuilder = SimpleStatement.builder(
                    "SELECT * from quest.t_user where country=:country ALLOW FILTERING");
            statementBuilder.addNamedValue("country","ru");
            ResultSet resultSet = session.execute(statementBuilder.build());
            for (Row row : resultSet) {
                System.out.printf("%-5s%-5d%-15s%-15s%n",
                        row.getString("country"),
                        row.getLong("id"),
                        row.getString("login"),
                        row.getString("password"));
            }
        }
    }
}
