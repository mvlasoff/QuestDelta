package ua.com.javarush.quest.khmelov.config;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

public class Config {

    public DataBase dataBase = new DataBase();

    private static final String APPLICATION_YAML = "/application.yaml";
    private static volatile Config config;

    private Config() {
    }

    public static Config get() {
        Config result = config;
        if (Objects.isNull(config)) {
            synchronized (Config.class) {
                if (Objects.isNull(config)) {
                    result = createAndUpdateConfig();
                }
            }
        }
        return result;
    }

    private static Config createAndUpdateConfig() {
        try {
            config = new Config();
            YAMLMapper yamlMapper = new YAMLMapper();
            ObjectReader objectReader = yamlMapper.readerForUpdating(config);
            URL resource = Config.class.getResource(APPLICATION_YAML);
            Optional<URL> optionalURL = Optional.ofNullable(resource);
            if (optionalURL.isPresent()) {
                objectReader.readValue(optionalURL.get());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }

    public static class DataBase {
        public String folder = "db";
        public String json = "tree.json";
    }
}
