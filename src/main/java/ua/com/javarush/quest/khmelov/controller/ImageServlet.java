package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ua.com.javarush.quest.khmelov.service.AvatarService;

import java.io.Serial;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Scanner;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6551390997304892153L;
    private final AvatarService avatarService = AvatarService.INSTANCE;

    @Override
    @SneakyThrows
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String requestURI = req.getRequestURI();
        String target = req.getContextPath() + "/images/";
        String nameImage = requestURI.replace(target, "");
        Optional<Path> file = avatarService.getAvatarPath(nameImage);
        if (file.isPresent()) {
            try (ServletOutputStream outputStream = resp.getOutputStream()) {
                Files.copy(file.get(), outputStream);
            }
        }
        Scanner scanner = new Scanner(System.in);
    }

}
