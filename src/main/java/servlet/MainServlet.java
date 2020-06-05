package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MainServlet", urlPatterns = "")
public class MainServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = UserServiceImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email != null && password != null) {
            User user = userService.getUserByEmail(email);
            if (user == null) {
                request.setAttribute("warnText", "Пользователь с таким email в базе не найден!");
                doGet(request, response);
            } else if (!password.equals(user.getPassword())) {
                request.setAttribute("warnText", "Неверный пароль, в авторизации отказано!");
                doGet(request, response);
            } else if (user.getRole() != null) {
                final HttpSession session = request.getSession();
                if (user.getRole().equals("admin")) {
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else if (user.getRole().equals("user")) {
                    session.setAttribute("user", user);
                    response.sendRedirect(request.getContextPath() + "/user");
                }
            } else {
                request.setAttribute("warnText", "Неверно задана роль запрашиваемого пользователя");
                doGet(request, response);
            }
        } else {
            request.setAttribute("warnText", "Повторите ввод данных (email + password)");
            doGet(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }
}
