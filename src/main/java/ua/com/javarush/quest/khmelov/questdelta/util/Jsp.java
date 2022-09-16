package ua.com.javarush.quest.khmelov.questdelta.util;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class Jsp {
    private Jsp() {}

    public static void reqRespForward(HttpServletRequest request, HttpServletResponse response, String jspName) throws ServletException, IOException {
        String path = "WEB-INF/%s.jsp".formatted(jspName);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(path);
        requestDispatcher.forward(request, response);
    }
}
