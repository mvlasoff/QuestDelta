package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.javarush.quest.khmelov.questdelta.entity.*;
import ua.com.javarush.quest.khmelov.questdelta.service.QuestService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@WebServlet("/space-quest")
public class SpaceQuestServlet extends HttpServlet {
    private Quest spaceQuest = new SpaceQuest();
    private QuestService questService = QuestService.getQuestService(spaceQuest);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession currentSession = req.getSession();
        Integer gamesplayed = (Integer) currentSession.getAttribute("gamesplayed");

        if(getId(req) < 0) {
            getFirstQuestion(req, resp);
        } else {
            getNextQuestion(req, resp, gamesplayed, currentSession);
        }
    }

    private void getNextQuestion(HttpServletRequest req, HttpServletResponse resp, Integer gamesplayed, HttpSession currentSession) throws ServletException, IOException {
        String id = req.getParameter("id");
        Optional<Question> optionalQuestion = questService.get(Long.parseLong(id));
        if (optionalQuestion.isPresent()) {
            Question nextQuestion = optionalQuestion.get();
            Collection<Answer> answers = questService.getAnswers(nextQuestion);

            if(answers.isEmpty()) {
                gamesplayed++;
                currentSession.setAttribute("gamesplayed", gamesplayed);
            }

            setQuestionAnswersAndForward(req, resp, nextQuestion, answers);
        }
    }

    private void getFirstQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question startQuestion = questService.getStartQuestion();
        Collection<Answer> answers = questService.getAnswers(startQuestion);
        setQuestionAnswersAndForward(req, resp, startQuestion, answers);
    }

    private static void setQuestionAnswersAndForward(HttpServletRequest req, HttpServletResponse resp, Question nextQuestion, Collection<Answer> answers) throws ServletException, IOException {
        req.setAttribute("answers", answers);
        req.setAttribute("question", nextQuestion);

        if(answers.isEmpty()) {
            req.setAttribute("end", true);
        }

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
