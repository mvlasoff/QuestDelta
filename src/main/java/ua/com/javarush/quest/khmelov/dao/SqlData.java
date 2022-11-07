package ua.com.javarush.quest.khmelov.dao;

public final class SqlData {

    public static final String sqlAllUsers = """
            SELECT *
              FROM "user";
            """;

    private SqlData() {
    }

    public static final String sqlDeleteTableUser = """
                    DROP TABLE IF EXISTS "user";
                    """;

    public static final String sqlCreateTableUser = """
            CREATE TABLE "user"
            (
                id       INT PRIMARY KEY,
                login    VARCHAR(128) UNIQUE,
                password VARCHAR(128) NOT NULL,
                role     VARCHAR(32)
            );
            """;

    public static final String sqlAddUsers = """
            INSERT INTO "user"(id, login, password, role)
            VALUES (1, 'Ivan', '456', 'ADMIN'),
                   (2, 'Andrew', '678', 'GUEST'),
                   (3, 'Elena', '123', 'USER');
                   """;
}
