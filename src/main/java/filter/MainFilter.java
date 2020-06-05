package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "MainFilter", urlPatterns = {"/"})
public class MainFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final HttpSession session = request.getSession(false);

        //Если залогинившийся пользователь посетил этот url - делаем соответствующий редирект
        if (session != null) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null) {
                if (user.getRole().equals("admin")) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                    return;
                } else {
                    response.sendRedirect(request.getContextPath() + "/user");
                    return;
                }
            }
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
