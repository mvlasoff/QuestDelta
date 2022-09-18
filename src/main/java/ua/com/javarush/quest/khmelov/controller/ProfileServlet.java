package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.service.AvatarService;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Optional;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(Go.PROFILE)
public class ProfileServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;

    @Override
    public void init() {
        getServletContext().setAttribute("roles", Role.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Jsp.getId(req.getSession());
        Optional<UserDto> user = userService.get(id);
        user.ifPresent(value -> req.setAttribute("user", value));
        Jsp.forward(req, resp, Go.PROFILE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        long id = Jsp.getId(req.getSession());
        Jsp.redirect(req, resp, Go.USER + "?id=" + id);
    }

}
