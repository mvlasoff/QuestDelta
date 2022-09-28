package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.questdelta.entity.Game;
import ua.com.javarush.quest.khmelov.questdelta.service.GameService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Map;

@WebServlet("/stat")
public class StatisticsServlet extends HttpServlet {
    private GameService gameService = GameService.getGameService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Long, Game> games = gameService.getGameRepository().getGames();
        Game spacegame = games.get(1L);
        Game javagame = games.get(2L);
        req.setAttribute("spacegame", spacegame);
        req.setAttribute("javagame", javagame);
        Jsp.reqRespForward(req, resp, "statistics");
    }
}
