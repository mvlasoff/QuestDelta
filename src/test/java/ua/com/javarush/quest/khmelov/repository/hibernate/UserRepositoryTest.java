package ua.com.javarush.quest.khmelov.repository.hibernate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.Repository;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest {

    private final Repository<User> userRepository= Winter.getBean(UserRepository.class);

    @Test
    void get() {
        User user = userRepository.get(1L);
        System.out.println(user);
        assertEquals(user.getLogin(),"Ivan");
    }

    @Test
    @DisplayName("When create+update+delete tempUser then no Exception")
    void createUpdateDelete() {
        User tempUser = User.with()
                .login("login_test")
                .password("password_test")
                .role(Role.GUEST)
                .build();
        userRepository.create(tempUser);
        System.out.println("CREATE " + tempUser);

        tempUser.setPassword("password_test_update");
        userRepository.update(tempUser);
        System.out.println("UPDATE " + tempUser);

        userRepository.delete(tempUser);
        System.out.println("DELETE " + tempUser);
        assertTrue(tempUser.getId() > 0);
    }
}