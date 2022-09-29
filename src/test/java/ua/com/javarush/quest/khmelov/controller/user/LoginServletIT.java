package ua.com.javarush.quest.khmelov.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import ua.com.javarush.quest.khmelov.config.Winter;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.entity.User;
import ua.com.javarush.quest.khmelov.mapping.Mapper;
import ua.com.javarush.quest.khmelov.repository.UserRepository;
import ua.com.javarush.quest.khmelov.util.Go;
import ua.com.javarush.quest.khmelov.util.Jsp;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.fail;

public class LoginServletIT {

    Map<String, String[]> formData = Map.of(
            "login", new String[]{"login"},
            "password", new String[]{"password"}
    );
    public static final User user = User.with()
            .id(1L)
            .login("login")
            .password("password")
            .role(Role.USER)
            .build();

    @Test
    void doPost() {
        //prepare db
        Winter.getBeen(UserRepository.class).create(user);
        UserDto userDto = Mapper.user.get(user).orElseThrow();
        //mock
        HttpSession httpSession = Mockito.mock(HttpSession.class);
        //stub
        HttpServletRequest requestStub = Mockito.mock(HttpServletRequest.class);
        Mockito.when(requestStub.getParameterMap()).thenReturn(formData);
        Mockito.when(requestStub.getSession()).thenReturn(httpSession);

        HttpServletResponse responseStub = Mockito.mock(HttpServletResponse.class);
        //static mock
        try (MockedStatic<Jsp> jsp = Mockito.mockStatic(Jsp.class)) {
            //Servlet with services and repo
            LoginServlet loginServlet = new LoginServlet();
            loginServlet.doPost(requestStub, responseStub);
            //assert forward (static method)
            jsp.verify(()->Jsp.redirect(requestStub, responseStub, Go.PROFILE));
            //assert addition userDto to session (instance method)
            Mockito.verify(httpSession).setAttribute(Jsp.Key.USER, userDto);
        } catch (ServletException | IOException e) {
            fail(e);
        }
    }
}