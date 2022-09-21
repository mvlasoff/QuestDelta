package ua.com.javarush.quest.khmelov.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

public enum Config {
    DB_FOLDER("db.folder", "db"),
    DB_JSON("db.json", "tree.json");

    private final String key;
    private String value;

    Config(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static final String APPLICATION_JSON = "/application.json";

    static {
        try {
            URL url = Config.class.getResource(APPLICATION_JSON);
            JsonNode tree = new ObjectMapper().readTree(url);
            Arrays.stream(Config.values())
                    .filter(line -> tree.has(line.key))
                    .forEach(line -> line.value = tree.get(line.key).asText());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return value;
    }

    public String get() {
        return value;
    }

    public static void main(String[] args) {
        System.out.println(DB_FOLDER + "/" + DB_JSON);
    }
}
