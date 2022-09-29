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
import static ua.com.javarush.quest.khmelov.util.Jsp.Key.USER;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.EDIT_USER)
public class EditUserServlet extends HttpServlet {

    private final UserService userService = Winter.getBean(UserService.class);
    private final ImageService imageService = Winter.getBean(ImageService.class);

    @Override
    public void init() {
        getServletContext().setAttribute(ROLES, Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (checkEditorInSession(req)) {
            long id = Parser.getId(req);
            Optional<UserDto> user = userService.get(id);
            user.ifPresent(value -> req.setAttribute(USER, value));
            Jsp.forward(req, resp, Go.EDIT_USER);
        } else {
            req.setAttribute(Jsp.Key.USERS, userService.getAll());
            Jsp.forward(req, resp, Go.USERS, "Недостаточно прав для редактирования");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        final String DEST = checkProfileEditor(req) ? Go.PROFILE : Go.USERS;
        if (checkEditorInSession(req)) {
            if (req.getParameter("update") != null) {
                userService.update(formData);
                imageService.uploadImage(req);
            } else if (req.getParameter("delete") != null) {
                userService.delete(formData);
            }
            Jsp.redirect(req, resp, DEST);
        } else {
            Jsp.forward(req, resp, DEST, "Недостаточно прав для редактирования");
        }
    }

    private boolean checkEditorInSession(HttpServletRequest req) {
        long id = Parser.getId(req);
        Optional<UserDto> editor = Parser.getUser(req.getSession());
        return editor.isPresent() &&
                (editor.get().getId() == id || editor.get().getRole() == Role.ADMIN);
    }

    static boolean checkProfileEditor(HttpServletRequest req) {
        long id = Parser.getId(req);
        Optional<UserDto> editor = Parser.getUser(req.getSession());
        return editor.isPresent() && (editor.get().getId() == id);
    }
}
