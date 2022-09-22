package ua.com.javarush.quest.khmelov.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.GameDto;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.service.GameService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;
import java.util.Optional;

@WebServlet(value = Go.GAME)
public class GameServlet extends HttpServlet {

    public static final GameService gameService = GameService.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Optional<UserDto> user = Parser.getUser(request.getSession());
        FormData formData = FormData.of(request);
        if (user.isPresent()) {
            Optional<GameDto> game = gameService.getGame(formData, user.get().getId());
            if (game.isPresent()) {
                GameDto gameDto = game.get();
                sendNextStep(request, response, gameDto);
                return;
            }
            Jsp.show(request, response, Go.QUESTS, "Нет такой игры");
        }
        Jsp.show(request, response, Go.QUESTS, "Сначала нужно войти в аккаунт");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long gameId = Parser.getId(request);
        Long answerId = Parser.getId(request, Jsp.Key.ANSWER);
        Optional<GameDto> game = gameService.checkAnswer(gameId,answerId);
        if (game.isPresent()) {
            GameDto gameDto = game.get();
            sendNextStep(request, response, gameDto);
            return;
        }
        Jsp.show(request, response, Go.QUESTS, "Нет такой игры");
    }

    private static void sendNextStep(HttpServletRequest request, HttpServletResponse response, GameDto gameDto) throws ServletException, IOException {
        request.setAttribute(Jsp.Key.GAME, gameDto);
        request.setAttribute(Jsp.Key.QUESTION, gameDto.getQuestion());
        Jsp.show(request, response, Go.GAME);
    }
}
