package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.service.AvatarService;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet({"/user", "/profile"})
public class UserServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 6978028341247457550L;
    private final UserService userService = UserService.INSTANCE;
    private final AvatarService avatarService = AvatarService.INSTANCE;

    @Override
    public void init() {
        getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = getId(req);
        Optional<UserDto> opUser = userService.get(id);
        opUser.ifPresent(value -> req.setAttribute("user", value));
        Jsp.forward(req, resp, "user");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id = getId(req);
        Part data = req.getPart("image");
        String image = "avatar-" + id;
        if (data.getInputStream().available() > 0) {
            avatarService.uploadAvatar(image, data.getInputStream());
        }
        String login = req.getParameter("login");
        String role = req.getParameter("role");
        if (req.getParameter("update") != null
                && userService.update(id, image, login, role)
        ) {
            Jsp.forward(req, resp, "profile");
        } else if (req.getParameter("delete") != null) {
            userService.delete(id);
            Jsp.forward(req, resp, "users");
        } else {
            throw new UnsupportedOperationException("not found cmd");
        }
    }

    private long getId(HttpServletRequest req) {
        return req.getParameter("id") != null
                ? Long.parseLong("0" + req.getParameter("id"))
                : 0L;
    }

}
