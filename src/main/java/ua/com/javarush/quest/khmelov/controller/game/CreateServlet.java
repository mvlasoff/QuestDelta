package ua.com.javarush.quest.khmelov.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.QuestDto;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.service.QuestService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = Go.CREATE)
public class CreateServlet extends HttpServlet {

    private final QuestService questService = Winter.getBean(QuestService.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<UserDto> user = Parser.getUser(request.getSession());
        if (user.isPresent()) {
            Jsp.forward(request, response, Go.CREATE);
        } else {
            Jsp.redirect(request, response, Go.LOGIN, "Сначала нужно войти в аккаунт");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<UserDto> user = Parser.getUser(request.getSession());
        FormData formData = FormData.of(request);
        if (user.isPresent()) {
            Optional<QuestDto> questDto = questService.create(formData, user.get().getId());
            if (questDto.isPresent()){
                Jsp.redirect(request, response, Go.QUESTS);
            } else {
                Jsp.redirect(request, response, Go.CREATE,"Некорректные данные квеста");
            }
        } else {
            Jsp.redirect(request, response, Go.LOGIN,"Сначала нужно войти в аккаунт");
        }
    }
}
