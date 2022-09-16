package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.io.Serial;
import java.util.Collection;

@WebServlet({"/", "/users"})
public class UsersServlet extends HttpServlet {

    @Serial
    private static final long serialVersionUID = 210439950951198312L;
    UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<UserDto> users = userService.getAll();
        req.setAttribute("users", users);
        Jsp.forward(req, resp, "users");
    }
}
