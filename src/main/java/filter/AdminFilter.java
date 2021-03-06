package filter;

import model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin/*"})
public class AdminFilter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) resp;

        final HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) request.getSession().getAttribute("user");
            if (user != null && user.getRole().equals("admin")) {
                chain.doFilter(req, resp);
                return;
            }
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/403.jsp").forward(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }
}
