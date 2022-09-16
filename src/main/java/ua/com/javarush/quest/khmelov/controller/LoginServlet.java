package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.util.Optional;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private final UserService userService=UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req,resp,"login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Optional<UserDto> optionalUser = userService.find(login, password);
        if (optionalUser.isPresent()){
            HttpSession session = req.getSession();
            UserDto user = optionalUser.get();
            session.setAttribute("user", user);
            Jsp.redirect(resp,"users");
        } else {
            req.setAttribute("error","User not found");
            Jsp.forward(req,resp,"login");
        }
    }
}
