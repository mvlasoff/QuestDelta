package ua.com.javarush.quest.khmelov.repository.memory;

import ua.com.javarush.quest.khmelov.entity.User;

import java.util.Comparator;
import java.util.stream.Stream;


public class UserRepository extends AbstractRepository<User> {

    @Override
    public Stream<User> find(User pattern) {
        return getAll()
                .filter(entity -> isOk(pattern, entity, User::getId)
                        && isOk(pattern, entity, User::getLogin)
                        && isOk(pattern, entity, User::getPassword)
                        && isOk(pattern, entity, User::getRole)
                )
                .sorted(Comparator.comparing(User::getLogin));
    }

}
