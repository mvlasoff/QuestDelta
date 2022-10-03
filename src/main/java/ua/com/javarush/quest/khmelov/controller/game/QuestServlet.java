package ua.com.javarush.quest.khmelov.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.dto.ui.QuestDto;
import ua.com.javarush.quest.khmelov.service.QuestService;
import ua.com.javarush.quest.khmelov.service.QuestionService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.khmelov.util.Jsp.Key.QUEST;

@WebServlet(value = {Go.QUEST},name = "QuestServlet")
public class QuestServlet extends HttpServlet {

    private final QuestService questService= Winter.getBean(QuestService.class);
    private final QuestionService questionService= Winter.getBean(QuestionService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req);
        Optional<QuestDto> questDto = questService.get(id);
        req.setAttribute(QUEST,questDto.orElseThrow());
        Jsp.forward(req, resp, Go.QUEST);
    }
}
