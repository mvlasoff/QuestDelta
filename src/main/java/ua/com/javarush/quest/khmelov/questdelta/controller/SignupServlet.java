package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.khmelov.questdelta.entity.Role;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;
import ua.com.javarush.quest.khmelov.questdelta.service.UserService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign-up")
public class SignupServlet extends HttpServlet {
    private final UserService userService = UserService.getUserService();

    private final static Logger log = LoggerFactory.getLogger(SignupServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("method in.");
        HttpSession session = req.getSession();
        session.setAttribute("roles", Role.values());

        log.info("method before forward.");
        Jsp.reqRespForward(req, resp, "signup");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("method in.");
        String login = req.getParameter("login");
        String roleStr = req.getParameter("role");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        if (password1 == null || password2 == null || password1.isBlank() || password2.isBlank()) {
            log.debug("passwords are null or blank.");
            Jsp.reqRespForward(req, resp, "signup");
        } else if (!password1.equals(password2)) {
            log.debug("passwords are not equal.");
            Jsp.reqRespForward(req, resp, "signup");
        } else if (roleStr == null || roleStr.isBlank() || login == null || login.isBlank()) {
            log.debug("role or login is null or blank.");
            Jsp.reqRespForward(req, resp, "signup");
        } else {
            Optional<User> user = userService.verify(login);
            if (user.isEmpty()) {
                Role role = Role.valueOf(roleStr);
                log.info("before posting new user to userService.");
                userService.doPost(login, password1, role);
            } else {
                log.debug("this user name already exists.");
                Jsp.reqRespForward(req, resp, "signup");
            }

            log.info("before forward.");
            Jsp.reqRespForward(req, resp, "login");
        }
    }
}
