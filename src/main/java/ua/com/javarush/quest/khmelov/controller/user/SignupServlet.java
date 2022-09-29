package ua.com.javarush.quest.khmelov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.service.ImageService;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.khmelov.util.Jsp.Key.ROLES;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.SIGNUP)
public class SignupServlet extends HttpServlet {

    private final UserService userService = Winter.getBeen(UserService.class);
    private final ImageService imageService = Winter.getBeen(ImageService.class);

    @Override
    public void init() {
        getServletContext().setAttribute(ROLES, Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req);
        Optional<UserDto> opUser = userService.get(id);
        opUser.ifPresent(value -> req.setAttribute("user", value));
        Jsp.forward(req, resp, Go.SIGNUP);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        userService.create(FormData.of(req));
        imageService.uploadImage(req);
        Jsp.redirect(req, resp, Go.USERS);
    }

}
