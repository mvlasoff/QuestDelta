package ua.com.javarush.quest.khmelov.questdelta.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ua.com.javarush.quest.khmelov.questdelta.entity.Role;
import ua.com.javarush.quest.khmelov.questdelta.entity.User;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryDBTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void whenGetUserByIdThenUserIsPulled() {
        User user = new User();
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Optional<User> optionalUser = mainUserRepository.get(1);
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            assertEquals(1, (long) user.getId());
        } else {
            assertEquals(1, (long) user.getId());
        }
    }

    @SuppressWarnings("SimplifiableAssertion")
    @Test
    void whenGetAllThenAllUsersPulled() {
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Collection<User> users = mainUserRepository.getAll();
        assertTrue(!users.isEmpty());
    }

    @Test
    void whenFindUserThenGetOne() {
        String login = "user", password = "12345";
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Optional<User> optional = mainUserRepository.find(login, password);
        assertTrue(optional.isPresent());
    }

    @SuppressWarnings({"OptionalGetWithoutIsPresent", "SimplifiableAssertion"})
    @Test
    void whenFindUserThenGetCorrectUser() {
        String login = "guest", password = "00000";
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Optional<User> optional = mainUserRepository.find(login, password);
        User user = optional.get();
        assertTrue(login.equals(user.getLogin()));
    }

    @Test
    void whenTryFindUserThenGetNull() {
        String login = "ewrtwyertytyutyurtui", password = "etwertwtyrtyeuyuytu";
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Optional<User> optional = mainUserRepository.find(login, password);
        assertTrue(optional.isEmpty());
    }

    @Test
    void whenTryToVerifyUserThenGetNull() {
        String login = "ewrtwyertytyutyurtui";
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Optional<User> optional = mainUserRepository.verify(login);
        assertTrue(optional.isEmpty());
    }

    @SuppressWarnings({"SimplifiableAssertion", "OptionalGetWithoutIsPresent"})
    @Test
    void whenVerifyUserThenGetCorrectUser() {
        String login = "admin";
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        Optional<User> optional = mainUserRepository.verify(login);
        User user = optional.get();
        assertTrue(login.equals(user.getLogin()));
    }

    @Test
    @Disabled
    void whenCreateThenShouldBeSavedInDB() {
        String login = "test1", password = "test2";
        MainUserRepository<User> mainUserRepository = UserRepositoryDB.get();
        mainUserRepository.create(login, password, Role.GUEST);
        Optional<User> optional = mainUserRepository.find(login, password);
        assertTrue(optional.isPresent());
    }
}