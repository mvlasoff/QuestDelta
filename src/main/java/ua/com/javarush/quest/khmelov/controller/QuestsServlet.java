package ua.com.javarush.quest.khmelov.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.dto.QuestDto;
import ua.com.javarush.quest.khmelov.service.QuestService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.util.Collection;

@WebServlet({Go.QUESTS})
public class QuestsServlet extends HttpServlet {

    QuestService questService=QuestService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<QuestDto> quests = questService.getAll();
        req.setAttribute("quests", quests);
        Jsp.forward(req, resp, Go.QUESTS);
    }
}
