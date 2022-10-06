package ua.com.javarush.quest.khmelov.config;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ua.com.javarush.quest.khmelov.exception.AppException;
import ua.com.javarush.quest.khmelov.service.ImageService;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.util.Objects;

public class Config {

    public DataBase dataBase = new DataBase();


    /**
     * multiplatform path extractor
     */
    public static final Path WEB_INF = Path.of(
            URI.create(
                    Objects.requireNonNull(
                            ImageService.class.getResource("/")
                    ).toString()
            )
    ).getParent();


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
            URL configYaml = Config.class.getResource(APPLICATION_YAML);
            if (Objects.nonNull(configYaml)) {
                ObjectReader objectReader = yamlMapper.readerForUpdating(config);
                objectReader.readValue(configYaml);
            }
        } catch (IOException e) {
            throw new AppException("config error", e);
        }
        return config;
    }

    public static class DataBase {
        public String folder = "db";
        public String json = "tree.json";
    }
}
