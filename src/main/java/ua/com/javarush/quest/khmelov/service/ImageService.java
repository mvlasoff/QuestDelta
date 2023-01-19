package ua.com.javarush.quest.khmelov.service;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.config.Config;
import ua.com.javarush.quest.khmelov.exception.AppException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ImageService {

    public static final String IMAGES_FOLDER = "images";
    public static final String PART_NAME = "image";
    public static final String NO_IMAGE_PNG = "no-image.png";
    public static final List<String> EXTENSIONS = List.of(".jpg", ".jpeg", ".png", ".bmp", ".gif", ".webp");

    private final Path imagesFolder = Config.WEB_INF.resolve(IMAGES_FOLDER);

    @SneakyThrows
    public ImageService() {
        Files.createDirectories(imagesFolder);
    }

    @SneakyThrows
    public void backup(Path destinationFolder) {
        Files.list(imagesFolder)
                .forEach(image -> {
                    try {
                        Files.copy(image, destinationFolder, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        throw new AppException(e.getMessage());
                    }
                });
    }


    @SneakyThrows
    public Optional<Path> getImagePath(String filename) {
        return EXTENSIONS.stream()
                .map(ext -> imagesFolder.resolve(filename + ext))
                .filter(Files::exists)
                .findAny()
                .or(() -> Optional.of(imagesFolder.resolve(NO_IMAGE_PNG)));
    }

    public void uploadImage(HttpServletRequest req, String imageId) throws IOException, ServletException {
        Part data = req.getPart(PART_NAME);
        if (Objects.nonNull(data) && data.getInputStream().available() > 0) {
            String filename = data.getSubmittedFileName();
            String ext = filename.substring(filename.lastIndexOf("."));
            deleteOldFiles(imageId);
            filename = imageId + ext;
            uploadImageInternal(filename, data.getInputStream());
        }
    }

    private void deleteOldFiles(String filename) {
        EXTENSIONS.stream()
                .map(ext -> imagesFolder.resolve(filename + ext))
                .filter(Files::exists)
                .forEach(p -> {
                    try {
                        Files.deleteIfExists(p);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    @SneakyThrows
    private void uploadImageInternal(String name, InputStream data) {
        try (data) {
            if (data.available() > 0) {
                Files.copy(data, imagesFolder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }

}
