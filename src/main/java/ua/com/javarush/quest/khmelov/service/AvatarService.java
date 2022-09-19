package ua.com.javarush.quest.khmelov.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.SneakyThrows;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

public enum AvatarService {
    INSTANCE;

    public static final String ROOT = "/";
    public static final String IMAGES_FOLDER = "images";
    public static final String PART_NAME = "image";
    public static final String FILENAME_PREFIX = "image-";
    public static final String NO_IMAGE_PNG = "no-image.png";
    private final Path imagesFolder = Path.of(Objects.requireNonNull(
                    AvatarService.class.getResource(ROOT)
            ).getPath())
            .getParent()
            .resolve(IMAGES_FOLDER);


    @SneakyThrows
    AvatarService() {
        Files.createDirectories(imagesFolder);
    }



    @SneakyThrows
    public Optional<Path> getAvatarPath(String filename) {
        return Files.exists(imagesFolder.resolve(filename))
                ? Optional.of(imagesFolder.resolve(filename))
                : Optional.of(imagesFolder.resolve(NO_IMAGE_PNG));
    }

    public void updateAvatar(HttpServletRequest req) throws IOException, ServletException {
        Part data = req.getPart(PART_NAME);
        if (data.getInputStream().available() > 0) {
            String id = req.getParameter("id");
            String filename = FILENAME_PREFIX + id;
            uploadAvatar(filename, data.getInputStream());
        }
    }

    @SneakyThrows
    private void uploadAvatar(String name, InputStream data) {
        try (data) {
            if (data.available() > 0)
                Files.copy(data, imagesFolder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
        }
    }

}
