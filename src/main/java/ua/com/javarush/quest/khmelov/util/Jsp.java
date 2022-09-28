package ua.com.javarush.quest.khmelov.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.Objects;

import static ua.com.javarush.quest.khmelov.util.Jsp.Key.ERROR_MESSAGE;

@UtilityClass
public class Jsp {

    public static final String WEB_INF_JSP = "WEB-INF%s.jsp";

    @UtilityClass
    public static class Key {
        public static final String ID = "id";

        public static final String USER = "user";
        public static final String USERS = "users";

        public static final String ROLES = "roles";

        public static final String QUEST = "quest";
        public static final String QUESTS = "quests";
        public static final String QUESTION = "question";

        public static final String NAME = "name";
        public static final String TEXT = "text";

        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String GAME = "game";
        public static final String ANSWER = "answer";
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp, String uri)
            throws ServletException, IOException {
        findErrorInSessionAfterForwardOrRedirect(req);
        String path = WEB_INF_JSP.formatted(uri);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp, String uri)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + uri);
    }

    public void forward(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMessage)
            throws ServletException, IOException {
        req.getSession().setAttribute(Key.ERROR_MESSAGE, errorMessage);
        forward(req,resp,uri);
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMessage)
            throws IOException {
        req.getSession().setAttribute(Key.ERROR_MESSAGE, errorMessage);
        resp.sendRedirect(req.getContextPath() + uri);
    }

    private void findErrorInSessionAfterForwardOrRedirect(HttpServletRequest req) {
        if (Objects.nonNull(req.getSession(false))) {
            HttpSession session = req.getSession();
            Object message = session.getAttribute(ERROR_MESSAGE);
            session.removeAttribute(ERROR_MESSAGE);
            req.setAttribute(ERROR_MESSAGE, message);
        }
    }


}
