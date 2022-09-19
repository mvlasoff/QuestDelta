package ua.com.javarush.quest.khmelov.util;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class JspTest {
    @Mock
    private HttpServletRequest req;

    @ParameterizedTest
    @CsvSource({
            "/,/",
            "profile/set/,/",
            "/get,/get",
            "profile/set,/set",
            "profile/set?id=1,/set"
    })
    void getCommand(String uri, String cmd) {
        Mockito.when(req.getRequestURI()).thenReturn(uri);
        String actual = Jsp.getCommand(req);
        Assertions.assertEquals(cmd, actual);
    }

}