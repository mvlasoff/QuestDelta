package ua.com.javarush.quest.khmelov.service;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.GameDto;
import ua.com.javarush.quest.khmelov.dto.ui.QuestionDto;
import ua.com.javarush.quest.khmelov.entity.*;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.*;

import java.util.Comparator;
import java.util.Optional;

public enum GameService {
    INSTANCE;

    private final Repository<Game> gameRepository = GameRepository.get();
    private final Repository<Quest> questRepository = QuestRepository.get();
    private final Repository<Question> questionRepository = QuestionRepository.get();
    private final Repository<Answer> answerRepository = AnswerRepository.get();

    private final QuestionService questionService = QuestionService.INSTANCE;

    public Optional<GameDto> getGame(FormData formData, Long userId) {
        Game gamePattern = Mapper.game.parse(formData);
        gamePattern.setGameState(GameState.PLAY);
        gamePattern.setUserId(userId);
        Optional<Game> currentGame = gameRepository
                .find(gamePattern)
                .max(Comparator.comparingLong(Game::getId));
        if (currentGame.isPresent()) {
            return fill(currentGame.get());
        } else if (gamePattern.getQuestId() != null) {
            return fill(getNewGame(userId, gamePattern.getQuestId()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<GameDto> checkAnswer(Long gameId, Long answerId) {
        Game game = gameRepository.get(gameId);
        if (game.getGameState() == GameState.PLAY) {
            Answer answer = answerRepository.get(answerId);
            Long nextQuestionId = answer != null
                    ? answer.getNextQuestionId()
                    : game.getCurrentQuestionId();
            game.setCurrentQuestionId(nextQuestionId);
            Question question = questionRepository.get(nextQuestionId);
            game.setGameState(question.getState());
            gameRepository.update(game);
        } else {
            game = getNewGame(game.getUserId(), game.getQuestId());
        }
        return fill(game);
    }

    private Game getNewGame(Long userId, Long questId) {
        Quest quest = questRepository.get(questId);
        Long startQuestionId = quest.getStartQuestionId();
        Question firstQuestion = questionRepository.get(startQuestionId);
        Game newGame = Game.with()
                .questId(questId)
                .currentQuestionId(startQuestionId)
                .gameState(firstQuestion.getState())
                .userId(userId) //from session
                .build();
        gameRepository.create(newGame);
        return newGame;
    }

    private Optional<GameDto> fill(Game game) {
        Optional<GameDto> gameDto = Mapper.game.get(game);
        Optional<QuestionDto> questionDto = questionService.get(game.getCurrentQuestionId());
        gameDto.orElseThrow().setQuestion(questionDto.orElseThrow());
        return gameDto;
    }
}
