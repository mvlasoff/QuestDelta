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
import java.util.Optional;

@WebServlet("/space-quest")
public class SpaceQuestServlet extends HttpServlet {

    QuestService questService = QuestService.getQuestService(new SpaceQuest());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Collection<Question> questions = questService.getAll();

        if(getId(req) < 0) {
            getFirstQuestion(req, resp);
        } else {
            getNextQuestion(req, resp);
        }
    }

    private void getNextQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Question> optionalQuestion = questService.get(Long.parseLong(id));
        if (optionalQuestion.isPresent()) {
            Question nextQuestion = optionalQuestion.get();
            Collection<Answer> answers = questService.getAnswers(nextQuestion);
            req.setAttribute("answers", answers);
            req.setAttribute("question", nextQuestion);
            Jsp.reqRespForward(req, resp, "spacequest");
        }
    }

    private void getFirstQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question startQuestion = questService.getStartQuestion();
        Collection<Answer> answers = questService.getAnswers(startQuestion);
        req.setAttribute("answers", answers);
        req.setAttribute("question", startQuestion);
        Jsp.reqRespForward(req, resp, "spacequest");
    }

    private long getId(HttpServletRequest req) {
        String id = req.getParameter("id");
        if(id == null || id.isBlank()) {
            return -1;
        }
        boolean isNumeric = id.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(id) : 0;
    }
}
