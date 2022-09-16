package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.questdelta.entity.Node;
import ua.com.javarush.quest.khmelov.questdelta.service.QuestService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/nodes")
public class NodesServlet extends HttpServlet {

    QuestService questService = QuestService.getNodeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Node> nodes = questService.getAll();
        req.setAttribute("nodes", nodes);
        Jsp.reqRespForward(req, resp, "nodes");
    }
}
