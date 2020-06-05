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
import java.util.List;

@WebServlet(name = "AdminServlet", urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = UserServiceImpl.getInstance();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> list = userService.getAllUsers();
        if (list.isEmpty()) {
            list = null; //Необходимо для обработки на jsp-страничке
        }
        request.setAttribute("users", list);
        getServletContext().getRequestDispatcher("/WEB-INF/views/admin.jsp").forward(request, response);
    }
}