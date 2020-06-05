package servlet;

import model.User;
import service.UserService;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/admin/del")
public class DeleteServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = UserServiceImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        if (userId != null) {
            userService.deleteUser(Long.valueOf(userId));
            response.sendRedirect(request.getContextPath() + "/admin");
            return;
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/404.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String url = "/WEB-INF/views/deleteUser.jsp";
        if (userId != null) {
            User user = userService.getUserById(Long.valueOf(userId));
            if (user != null) {
                request.setAttribute("user", user);
                request.getServletContext().getRequestDispatcher(url).forward(request, response);
                return;
            }
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/404.jsp").forward(request, response);
    }
}
