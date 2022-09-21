package ua.com.javarush.quest.khmelov.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.experimental.UtilityClass;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.exception.AppException;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Jsp {

    @UtilityClass
    public static class Key {
        public static final String ID = "id";

        public static final String USER = "user";
        public static final String USERS = "users";

        public static final String ROLES = "roles";

        public static final String QUEST = "quest";
        public static final String QUESTS = "quests";

        public static final String ERROR_MESSAGE = "errorMessage";
    }


    public void forward(HttpServletRequest request, HttpServletResponse response, String uriString)
            throws ServletException, IOException {
        String path = "WEB-INF%s.jsp".formatted(uriString);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public void showError(HttpServletRequest request, HttpServletResponse response, String uriString, String errorMessage)
            throws ServletException, IOException {
        String path = "WEB-INF%s.jsp".formatted(uriString);
        request.setAttribute(Key.ERROR_MESSAGE, errorMessage);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response, String uri)
            throws IOException {
        response.sendRedirect(request.getContextPath() + uri);
    }

    public String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile(".*(/[a-z-]*)").matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new AppException("incorrect uri: " + uri);
        }

    }

    public Long getId(HttpServletRequest req) {
        String id = req.getParameter(Key.ID);
        return id != null && !id.isBlank()
                ? Long.parseLong(id)
                : 0L;
    }

    public Long getId(HttpSession session) {
        Object user = session.getAttribute(Key.USER);
        return user != null
                ? ((UserDto) user).getId()
                : 0L;
    }


    public Optional<UserDto> getUser(HttpSession session) {
        Object user = session.getAttribute(Key.USER);
        return user != null
                ? Optional.of((UserDto) user)
                : Optional.empty();
    }
}
