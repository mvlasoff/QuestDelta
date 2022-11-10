package ua.com.javarush.quest.khmelov.repository.dao.init;

public final class SqlData {

    public static final String SQL_ALL_USERS = """
            SELECT *
              FROM "user";
            """;

    private SqlData() {
    }

    public static final String SQL_DELETE_TABLE_USER = """
                    DROP TABLE IF EXISTS "user";
                    """;

    public static final String SQL_CREATE_TABLE_USER = """
            CREATE TABLE "user"
            (
                id       SERIAL PRIMARY KEY,
                login    VARCHAR(128) UNIQUE,
                password VARCHAR(128) NOT NULL,
                role     VARCHAR(32)
            );
            """;

    public static final String SQL_ADD_USERS = """
            INSERT INTO "user"(login, password, role)
            VALUES ('Ivan', '456', 'ADMIN'),
                   ('Andrew', '678', 'GUEST'),
                   ('Elena', '123', 'USER');
                   """;
}
