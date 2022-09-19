package ua.com.javarush.quest.khmelov.config;

import lombok.experimental.UtilityClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Properties;

@UtilityClass
public class ConfigLoader {

    private static final String APP_PROPERTIES = "app.properties";
    private static final String DATABASE_KEY = "database.path";

    private static final String DATABASE_FOLDER;
    private static final URL rootUrl = ConfigLoader.class.getResource("/");
    private static final String strRoot = Objects.requireNonNull(rootUrl).getPath();
    private final Path root = Path.of(strRoot);


    private static final Properties properties;


    static {
        properties = new Properties();
        try (BufferedReader reader = Files.newBufferedReader(root.resolve(APP_PROPERTIES));) {
            properties.load(reader);
            DATABASE_FOLDER = properties.getProperty(DATABASE_KEY);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path getRootPath() {
        return root;
    }

    public Path getDatabasePath() {
        return root.resolve(DATABASE_FOLDER);
    }


}
