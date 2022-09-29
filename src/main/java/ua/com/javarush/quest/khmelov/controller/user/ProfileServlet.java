package ua.com.javarush.quest.khmelov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;

import static ua.com.javarush.quest.khmelov.util.Jsp.Key.USER;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.PROFILE)
public class ProfileServlet extends HttpServlet {

    private final UserService userService = Winter.getBean(UserService.class);

    @Override
    public void init() {
        getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req.getSession());
        userService
                .get(id)
                .ifPresent(value -> req.setAttribute(USER, value));
        Jsp.forward(req, resp, Go.PROFILE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (EditUserServlet.checkProfileEditor(req)) {
            Jsp.redirect(req, resp, Go.EDIT_USER + "?id=" + Parser.getId(req));
        } else {
            Jsp.forward(req, resp, Go.PROFILE, "Недостаточно прав для редактирования");
        }
    }

}
