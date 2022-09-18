package ua.com.javarush.quest.khmelov.dto;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class FormData {

    private final Map<String, String[]> parameterMap;

    private FormData(HttpServletRequest request) {
        parameterMap = request.getParameterMap();
    }

    public static FormData of(HttpServletRequest request) {
        return new FormData(request);
    }

    public String getParameter(String name) {
        return parameterMap.getOrDefault(name, new String[1])[0];
    }

    public Long getId() {
        return parameterMap.containsKey("id")
                ? Long.valueOf(getParameter("id"))
                : null;
    }

    @Override
    public String toString() {
        return parameterMap.entrySet()
                .stream()
                .map(e -> e.getKey() + "=" + Arrays.toString(e.getValue()))
                .collect(Collectors.joining());
    }
}
