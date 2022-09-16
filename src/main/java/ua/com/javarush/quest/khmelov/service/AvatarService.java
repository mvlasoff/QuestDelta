package ua.com.javarush.quest.khmelov.service;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.Optional;

public enum AvatarService {
    INSTANCE;

    public static final String NO_IMAGE_PNG = "no-image.png";
    public static final String ROOT = "/";
    public static final String IMAGES_FOLDER = "images";
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
    public void uploadAvatar(String name, InputStream data) {
        try (data) {
            if (data.available() > 0)
                Files.copy(data, imagesFolder.resolve(name), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<Path> getAvatarPath(String filename) {
        return Files.exists(imagesFolder.resolve(filename))
                ? Optional.of(imagesFolder.resolve(filename))
                : Optional.of(imagesFolder.resolve(NO_IMAGE_PNG));
    }

    public static void main(String[] args) {
        Path images = Path.of(Objects.requireNonNull(
                AvatarService.class.getResource(ROOT)).getPath())
                .getParent()
                .resolve(IMAGES_FOLDER);
        System.out.println(images.resolve(NO_IMAGE_PNG));

        String filename = NO_IMAGE_PNG;
        System.out.println(INSTANCE.getAvatarPath(filename));
        System.out.println(Files.exists(INSTANCE.imagesFolder.resolve(filename)));
    }

}
