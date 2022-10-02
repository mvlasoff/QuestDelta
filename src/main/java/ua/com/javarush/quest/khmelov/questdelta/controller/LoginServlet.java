package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;
import ua.com.javarush.quest.khmelov.questdelta.service.UserService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/log-in")
public class LoginServlet extends HttpServlet {
    private final UserService userService = UserService.getUserService();
    private final static Logger log = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("method in and before forward.");
        Jsp.reqRespForward(req, resp, "login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("method in.");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login != null && password != null) {
            Optional<User> user = userService.find(login, password);
            if (user.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user.get());
            }
            log.debug("password is incorrect.");
        } else {
            log.debug("login or password is null.");
        }

        log.info("before forward.");
        Jsp.reqRespForward(req, resp, "login");
    }
}
