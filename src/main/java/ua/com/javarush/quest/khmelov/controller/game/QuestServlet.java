package ua.com.javarush.quest.khmelov.controller.game;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.QuestDto;
import ua.com.javarush.quest.khmelov.dto.ui.QuestionDto;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.service.ImageService;
import ua.com.javarush.quest.khmelov.service.QuestService;
import ua.com.javarush.quest.khmelov.service.QuestionService;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;
import java.util.Optional;

import static ua.com.javarush.quest.khmelov.util.Jsp.Key.QUEST;

@MultipartConfig(fileSizeThreshold = 1 << 20)
@WebServlet(value = {Go.QUEST}, name = "QuestServlet")
public class QuestServlet extends HttpServlet {

    private final QuestService questService = Winter.getBean(QuestService.class);
    private final QuestionService questionService = Winter.getBean(QuestionService.class);
    private final ImageService imageService = Winter.getBean(ImageService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Parser.getId(req);
        Optional<QuestDto> questDto = questService.get(id);
        req.setAttribute(QUEST, questDto.orElseThrow());
        Jsp.forward(req, resp, Go.QUEST);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        FormData formData = FormData.of(req);
        Optional<UserDto> editor = Parser.getUser(req.getSession());
        if (editor.isPresent() && editor.get().getRole() == Role.ADMIN) {
            Long id = Parser.getId(req);
            Long idQuest = Parser.getId(req, "idQuest");
            Optional<QuestionDto> questionDto = questionService.update(formData);
            if (questionDto.isPresent()) {
                imageService.uploadImage(req, questionDto.get().getImage());
            }
            String uri = "%s?id=%d#q%d".formatted(Go.QUEST, idQuest, id);
            Jsp.redirect(req, resp, uri);
        } else {
            Jsp.forward(req, resp, Go.QUEST, "Недостаточно прав для редактирования");
        }
    }
}
