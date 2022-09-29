package ua.com.javarush.quest.khmelov.service;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.quest.khmelov.dto.FormData;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.repository.UserRepository;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    public static final Long ID = 999L;
    public static final Map<String, String[]> MAP = Map.of(
            "id", new String[]{ID.toString()}
    );

    @Mock
    UserRepository userRepositoryMock;

    @Mock
    HttpServletRequest requestStub;

    private UserService userService;
    private User user;
    private User userIdOnly;
    private FormData formData;

    @BeforeEach
    void createService() {
        userService = new UserService(userRepositoryMock);
        user = User.with()
                .id(ID)
                .login("Test")
                .password("Test")
                .role(Role.USER)
                .build();
        userIdOnly = User.with()
                .id(ID)
                .build();
        Mockito.when(requestStub.getParameterMap()).thenReturn(MAP);
        formData = FormData.of(requestStub);
    }

    @Test
    @DisplayName("when get id then return user from repo")
    void get() {
        Mockito.when(userRepositoryMock.get(ID)).thenReturn(user);
        Optional<UserDto> userDto = userService.get(ID);
        Assertions.assertEquals(userDto.orElseThrow().getLogin(), user.getLogin());
        Mockito.verify(userRepositoryMock).get(ID);
    }

    @Test
    @DisplayName("when find by form data, then return dto")
    void find() {
        Mockito.when(userRepositoryMock.find(userIdOnly)).thenReturn(Stream.of(user));
        Optional<UserDto> userDto = userService.find(formData);
        Assertions.assertEquals(userDto.orElseThrow().getLogin(), user.getLogin());
        Mockito.verify(requestStub).getParameterMap();
        Mockito.verify(userRepositoryMock).find(userIdOnly);
    }

    @Test
    void delete() {
        Mockito.when(userRepositoryMock.get(ID)).thenReturn(user, userIdOnly);
        userService.delete(formData);
        Mockito.verify(userRepositoryMock).delete(user);
        userService.delete(formData);
        Mockito.verify(userRepositoryMock).delete(userIdOnly);
    }
}