package ua.com.javarush.quest.khmelov.util;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;


@ExtendWith(MockitoExtension.class)
class ParserTest {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @ParameterizedTest
    @CsvSource({
            "/,/",
            "profile/set/,/",
            "/get,/get",
            "profile/set,/set",
            "profile/set?id=1,/set",
    })
    @DisplayName("check extract command from uri")
    void getCommand(String uri, String expectedCommand) {
        Mockito.when(request.getRequestURI()).thenReturn(uri);
        String actualCommand = Parser.getCommand(request);
        Assertions.assertEquals(expectedCommand, actualCommand);
    }

    @ParameterizedTest
    @CsvSource({
            "questId,123,123",
            "id,0,0",
    })
    @DisplayName("check extract parameter from request")
    void getId(String key, String value, Long expectedId) {
        Mockito.when(request.getParameter(key)).thenReturn(value);
        Long actual = Parser.getId(request, key);
        Assertions.assertEquals(expectedId, actual);
    }

    @Test
    void getUser() {
        UserDto userDto = UserDto.with().login("test").build();
        Mockito.when(session.getAttribute("user")).thenReturn(userDto);
        UserDto actual = Parser.getUser(session).orElseThrow();
        Assertions.assertEquals(userDto.getLogin(), actual.getLogin());
    }
}