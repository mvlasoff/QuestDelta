package ua.com.javarush.quest.khmelov.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import lombok.SneakyThrows;

@WebFilter(value = "/*", initParams = @WebInitParam(name = "code", value = "UTF-8"))
public class Encoder implements Filter {

    private String code;

    @Override
    public void init(FilterConfig filterConfig) {
        code = filterConfig.getInitParameter("code");
    }


    @Override
    @SneakyThrows
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
        String encoding = request.getCharacterEncoding();
        if (!code.equalsIgnoreCase(encoding)) {
            request.setCharacterEncoding(code);
        }

        encoding = response.getCharacterEncoding();
        if (!code.equalsIgnoreCase(encoding)) {
            response.setCharacterEncoding(code);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
