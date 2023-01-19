package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.service.InitService;
import ua.com.javarush.quest.khmelov.service.UserService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(value = Go.STAT, name = "StatServlet")
public class StatServlet extends HttpServlet {

    UserService userService = Winter.getBean(UserService.class);
    InitService initService = Winter.getBean(InitService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("statistics", userService.getStat());
        Jsp.forward(request,response,Go.STAT);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        initService.save();
        Jsp.redirect(request,response,Go.STAT,"Сохранено: "+ LocalDateTime.now());
    }
}
