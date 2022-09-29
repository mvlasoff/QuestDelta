package ua.com.javarush.quest.khmelov.questdelta.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.questdelta.entity.*;
import ua.com.javarush.quest.khmelov.questdelta.service.QuestService;
import ua.com.javarush.quest.khmelov.questdelta.util.Jsp;

import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

@WebServlet("/space-quest")
public class SpaceQuestServlet extends HttpServlet {
    private QuestService questService = QuestService.getQuestService();

    //private GameService gameService = GameService.getGameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(getId(req) < 0) {
            getFirstQuestion(req, resp);
        } else {
            getNextQuestion(req, resp);
        }
    }

    private void getNextQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("user");

        Optional<Question> optionalQuestion = questService.get(1L, Long.parseLong(id));
        if (optionalQuestion.isPresent()) {
            Question nextQuestion = optionalQuestion.get();
            Collection<Answer> answers = questService.getAnswers(1L, nextQuestion);

            if(answers.isEmpty()) {
                user.getGameStatistics().setGamesCount(1L);
                if(nextQuestion.isWon()) {
                    user.getGameStatistics().setGamesWon(1L);
                }
            }

            req.getSession().setAttribute("user", user);
            setQuestionAnswersAndForward(req, resp, nextQuestion, answers);
        }
    }

    private void setQuestionAnswersAndForward(HttpServletRequest req, HttpServletResponse resp, Question nextQuestion, Collection<Answer> answers) throws ServletException, IOException {
        req.setAttribute("answers", answers);
        req.setAttribute("question", nextQuestion);

        if(answers.isEmpty()) {
            req.setAttribute("end", true);
        }

        Jsp.reqRespForward(req, resp, "spacequest");
    }

    private void getFirstQuestion(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Question startQuestion = questService.getStartQuestion(1L);
        Collection<Answer> answers = questService.getAnswers(1L, startQuestion);
        setQuestionAnswersAndForward(req, resp, startQuestion, answers);
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
