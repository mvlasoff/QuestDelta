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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Jsp {

    public void forward(HttpServletRequest request, HttpServletResponse response, String uriString)
            throws ServletException, IOException {
        String path = "WEB-INF/%s.jsp".formatted(uriString);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response, String uri)
            throws IOException {
        response.sendRedirect(request.getContextPath() + uri);
    }

    public String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile(".*(/[a-z]*)").matcher(uri);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            throw new AppException("incorrect uri: " + uri);
        }

    }

    public long getId(HttpServletRequest req) {
        return req.getParameter("id") != null
                ? Long.parseLong("0" + req.getParameter("id"))
                : 0L;
    }

    public long getId(HttpSession session) {
        Object user = session.getAttribute("user");
        return user != null
                ? ((UserDto) user).getId()
                : 0L;
    }
}
