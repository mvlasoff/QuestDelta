package ua.com.javarush.quest.khmelov.service;

import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.UserDto;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.Repository;
import ua.com.javarush.quest.khmelov.repository.UserRepository;

import java.util.Collection;
import java.util.Optional;

public enum UserService {

    INSTANCE;

    private final Repository<User> userRepository = UserRepository.get();


    public Collection<UserDto> getAll() {
        return userRepository.getAll()
                .map(Mapper.user::write)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList();
    }

    public Optional<UserDto> get(long id) {
        User user = userRepository.get(id);
        return Mapper.user.write(user);
    }

    public Optional<UserDto> find(FormData formData) {
        User user = Mapper.user.read(formData);
        Optional<User> optionalUser = userRepository
                .find(user)
                .findFirst();
        return optionalUser.isPresent()
                ? Mapper.user.write(optionalUser.get())
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
}
