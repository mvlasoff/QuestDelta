package ua.com.javarush.quest.khmelov.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.javarush.quest.khmelov.dto.ui.UserDto;
import ua.com.javarush.quest.khmelov.entity.Game;
import ua.com.javarush.quest.khmelov.entity.Role;
import ua.com.javarush.quest.khmelov.util.Jsp;
import ua.com.javarush.quest.khmelov.util.Parser;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static ua.com.javarush.quest.khmelov.util.Go.*;


@WebFilter({ROOT, USERS, LOGIN, SIGNUP, PROFILE, LOGOUT, EDIT_USER, GAME})
public class RoleSelector implements Filter {

    private final Map<Role, List<String>> uriMap = Map.of(
            Role.GUEST, List.of(ROOT, USERS, LOGIN, SIGNUP, GAME),
            Role.USER, List.of(ROOT, USERS, LOGIN, SIGNUP, PROFILE, LOGOUT, EDIT_USER, GAME),
            Role.ADMIN, List.of(ROOT, USERS, LOGIN, SIGNUP, PROFILE, LOGOUT, EDIT_USER, GAME)
    );

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Object user = request.getSession().getAttribute("user");
        Role role = (Objects.isNull(user))
                ? Role.GUEST
                : ((UserDto) user).getRole();
        String command = Parser.getCommand(request);
        if (uriMap.get(role).contains(command)){
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            Jsp.redirect(request,response,ROOT);
        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
