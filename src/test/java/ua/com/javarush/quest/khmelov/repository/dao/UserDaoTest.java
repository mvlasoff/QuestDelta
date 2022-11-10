package ua.com.javarush.quest.khmelov.repository.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserDaoTest {

    private UserDao userDao;

    @BeforeEach
    public void setUp() {
        userDao = new UserDao();
    }

    public static Stream<Arguments> getSamplePatternForSearch() {
        //several users with different nullable fields (need skipped)
        return Stream.of(
                Arguments.of(User.with().login("Elena").password("123").build(), 1),
                Arguments.of(User.with().login("Elena").password("badpass").build(), 0),
                Arguments.of(User.with().login("Elena").build(), 1),

                Arguments.of(User.with().login("Andrew").build(), 1),
                Arguments.of(User.with().password("789").build(), 1),
                Arguments.of(User.with().role(Role.GUEST).build(), 1),

                Arguments.of(User.with().login("Ivan").password("456").build(), 1),
                Arguments.of(User.with().login("Ivan").password("456").role(Role.ADMIN).build(), 1),

                Arguments.of(User.with().build(), 3),
                Arguments.of(User.with().id(0L).build(), 0)
        );
    }

    @Test
    @DisplayName("When get all count=3")
    void getAll() {
        long count = userDao.getAll().count();
        assertEquals(3, count);
    }

    @ParameterizedTest
    @MethodSource("getSamplePatternForSearch")
    @DisplayName("Check find by not null fields")
    public void find(User user, int count) {
        long actualCount = userDao.find(user).count();
        assertEquals(count, actualCount);
    }

    @Test
    @DisplayName("When find by id then get user id=1 role=ADMIN")
    void get() {
        User user = userDao.get(1L);
        assertEquals(1L, user.getId());
        assertEquals(Role.ADMIN, user.getRole());
    }

    @Test
    @DisplayName("When create+update+delete tempUser then no Exception")
    void createUpdateDelete() {
        User tempUser = User.with()
                .login("login_test")
                .password("password_test")
                .role(Role.GUEST)
                .build();
        userDao.create(tempUser);
        System.out.println("CREATE " + tempUser);

        tempUser.setPassword("password_test_update");
        userDao.update(tempUser);
        System.out.println("UPDATE " + tempUser);

        userDao.delete(tempUser);
        System.out.println("DELETE " + tempUser);
        assertTrue(tempUser.getId() > 0);
    }


}