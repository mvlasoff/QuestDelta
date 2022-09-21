package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.questdelta.entity.Answer;
import ua.com.javarush.quest.khmelov.questdelta.entity.Question;
import ua.com.javarush.quest.khmelov.questdelta.entity.SpaceQuest;
import ua.com.javarush.quest.khmelov.questdelta.service.QuestService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Collection;

@WebServlet("/space-quest")
public class SpaceQuestServlet extends HttpServlet {

    QuestService questService = QuestService.getQuestService(new SpaceQuest());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Collection<Question> questions = questService.getAll();
        Question startQuestion = questService.getStartQuestion();
        Collection<Answer> answers = questService.getAnswers(startQuestion);
        //req.setAttribute("questions", questions);
        req.setAttribute("answers", answers);
        req.setAttribute("firstQuestion", startQuestion);
        Jsp.reqRespForward(req, resp, "spacequest");
    }
}
