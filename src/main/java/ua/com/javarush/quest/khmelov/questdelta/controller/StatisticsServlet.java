package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.questdelta.entity.Game;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.List;

@WebServlet("/stat")
public class StatisticsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<Game> games = user.getGames();
        Game spacegame = games.get(0);
        Game javagame = games.get(1);
        req.setAttribute("spacegame", spacegame);
        req.setAttribute("javagame", javagame);
        Jsp.reqRespForward(req, resp, "statistics");
    }
}
