package ua.com.javarush.quest.khmelov.service;

import lombok.AllArgsConstructor;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.StatDto;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.entity.Game;
import ua.com.javarush.quest.khmelov.entity.GameState;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.impl.GameRepository;
import ua.com.javarush.quest.khmelov.repository.impl.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    public Collection<UserDto> getAll() {
        return userRepository.getAll()
                .map(Mapper.user::get)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<UserDto> get(long id) {
        User user = userRepository.get(id);
        return Mapper.user.get(user);
    }

    public Optional<UserDto> find(FormData formData) {
        User user = Mapper.user.parse(formData);
        Optional<User> optionalUser = userRepository
                .find(user)
                .findFirst();
        return optionalUser.isPresent()
                ? Mapper.user.get(optionalUser.get())
                : Optional.empty();
    }

    public void update(FormData formData) {
        User user = userRepository.get(formData.getId());
        Mapper.user.fill(user, formData);
        userRepository.update(user);
    }

    public void create(FormData formData) {
        User user = new User();
        Mapper.user.fill(user, formData);
        userRepository.create(user);
    }

    public void delete(FormData formData) {
        User user = userRepository.get(formData.getId());
        userRepository.delete(user);
    }

    public Collection<StatDto> getStat() {
        return userRepository.getAll().map(this::getStatDto).toList();
    }

    private StatDto getStatDto(User user) {
        Game pattern = Game.with().userId(user.getId()).build();
        List<Game> games = gameRepository.find(pattern).toList();
        long win = games.stream().filter(game -> game.getGameState().equals(GameState.WIN)).count();
        long lost = games.stream().filter(game -> game.getGameState().equals(GameState.LOST)).count();
        long play = games.stream().filter(game -> game.getGameState().equals(GameState.PLAY)).count();
        return StatDto.with()
                .login(user.getLogin())
                .win(win)
                .lost(lost)
                .play(play)
                .total(win + lost + play)
                .build();
    }
}
