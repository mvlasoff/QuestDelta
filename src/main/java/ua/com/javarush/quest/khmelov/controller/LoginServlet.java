package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.util.Optional;


@WebServlet(Go.LOGIN)
public class LoginServlet extends HttpServlet {

    private final UserService userService = UserService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Jsp.forward(req, resp, Go.LOGIN);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<UserDto> optionalUser = userService.find(FormData.of(req));
        if (optionalUser.isPresent()) {
            req.getSession().setAttribute("user", optionalUser.get());
            Jsp.redirect(req, resp, Go.PROFILE);
        } else {
            req.setAttribute("error", "User not found");
            Jsp.forward(req, resp, Go.LOGIN);
        }
    }
}
