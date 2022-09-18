package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.exception.AppException;
import ua.com.javarush.quest.khmelov.service.AvatarService;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.USER)
public class UserServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;
    private final AvatarService avatarService = AvatarService.INSTANCE;

    @Override
    public void init() {
        getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Jsp.getId(req);
        Optional<UserDto> opUser = userService.get(id);
        opUser.ifPresent(value -> req.setAttribute("user", value));
        Jsp.forward(req, resp, Go.USER);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        if (req.getParameter("update") != null) {
            userService.update(formData);
            avatarService.updateAvatar(req);
            Jsp.redirect(req, resp, Go.PROFILE);
        } else if (req.getParameter("delete") != null) {
            userService.delete(formData);
            Jsp.redirect(req, resp, Go.USERS);
        } else {
            throw new AppException("not found cmd in " + formData);
        }
    }
}
