package ua.com.javarush.quest.khmelov.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class Jsp {
    public void forward(HttpServletRequest request, HttpServletResponse response, String uriString)
            throws ServletException, IOException {
        String path = uriString.contains(".jsp")
                ? "WEB-INF/%s".formatted(uriString)
                : "WEB-INF/%s.jsp".formatted(uriString);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }

    public void redirect(HttpServletResponse response, String uri)
            throws IOException {
        response.sendRedirect(uri);
    }

    public String getCommand(HttpServletRequest request) {
        String uri = request.getRequestURI();
        Matcher matcher = Pattern.compile("/[a-z]*").matcher(uri);
        if (matcher.find()) {
            return matcher.group();
        } else {
            throw new UnsupportedOperationException("incorrect uri" + uri);
        }
    }

}
