package ua.com.javarush.quest.khmelov.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;

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

        public static final String ERROR_MESSAGE = "errorMessage";
        public static final String GAME = "game";
        public static final String ANSWER = "answer";
    }

    public void show(HttpServletRequest req, HttpServletResponse resp, String uri)
            throws ServletException, IOException {
        String path = WEB_INF_JSP.formatted(uri);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    public void show(HttpServletRequest req, HttpServletResponse resp, String uri, String errorMessage)
            throws ServletException, IOException {
        req.setAttribute(Key.ERROR_MESSAGE, errorMessage);
        show(req,resp,uri);
    }

    public void redirect(HttpServletRequest req, HttpServletResponse resp, String uri)
            throws IOException {
        resp.sendRedirect(req.getContextPath() + uri);
    }


}
