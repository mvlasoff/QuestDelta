package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.khmelov.questdelta.entity.Role;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;
import ua.com.javarush.quest.khmelov.questdelta.repository.GameStatistics;
import ua.com.javarush.quest.khmelov.questdelta.service.UserService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Optional;

@WebServlet("/sign-up")
public class SignupServlet extends HttpServlet {
    private UserService userService = UserService.getUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("roles", Role.values());

        Jsp.reqRespForward(req, resp, "signup");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = (String) req.getParameter("login");
        String roleStr = (String) req.getParameter("role");
        String password1 = (String) req.getParameter("password1");
        String password2 = (String) req.getParameter("password2");

        if (password1 == null || password2 == null || password1.isBlank() || password2.isBlank()) {
            Jsp.reqRespForward(req, resp, "signup");
        }

        if (password1 != null && !password1.equals(password2)) {
            Jsp.reqRespForward(req, resp, "signup");
        }

        if (password1 != null && !password1.equals(password2)) {
            Jsp.reqRespForward(req, resp, "signup");
        }

        if (roleStr == null || login == null || login.isBlank()) {
            Jsp.reqRespForward(req, resp, "signup");
        }

        Optional<User> user = userService.find(login, password1);
        if (user.isEmpty()) {
            Role role = Role.valueOf(roleStr);
            userService.doPost(login, password1, role);
        }

        Jsp.reqRespForward(req, resp, "login");
    }
}
