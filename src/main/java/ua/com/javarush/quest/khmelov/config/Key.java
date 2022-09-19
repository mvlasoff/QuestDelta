package ua.com.javarush.quest.khmelov.config;

import java.io.File;

public enum Key {
    DB_FOLDER("database.folder", "db"),
    DB_JSON_PATH("database.folder", "db" + File.separator + "tree.json");

    private final String key;
    public final String value;

    Key(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return key;
    }
}
