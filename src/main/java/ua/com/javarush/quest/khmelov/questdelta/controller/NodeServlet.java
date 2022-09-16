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
import java.util.Optional;

@WebServlet("/node")
public class NodeServlet extends HttpServlet {

    QuestService questService = QuestService.getNodeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<String> stringId = Optional.ofNullable(req.getParameter("id"));
        if(stringId.isPresent()) {
            long id = Long.parseLong(stringId.get());
            Optional<Node> node = questService.get(id);
            if(node.isPresent()) {
                req.setAttribute("node", node.get());
            }
        }
        Jsp.reqRespForward(req, resp, "node");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
