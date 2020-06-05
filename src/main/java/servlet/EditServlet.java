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

@WebServlet(name = "EditServlet", urlPatterns = "/admin/edit")
public class EditServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = UserServiceImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String userAge = request.getParameter("age");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (userId != null && userAge != null && firstName != null && lastName != null
                && email != null && password != null && role != null) {
            User user = new User(Long.valueOf(userId), firstName.trim(),
                    lastName.trim(), Integer.valueOf(userAge), email, password, role);
            User userByEmail = userService.getUserByEmail(email);
            if (userByEmail != null) {
                if (userByEmail.getId() == user.getId()) {
                    userService.editUser(user);
                    response.sendRedirect(request.getContextPath() + "/admin");
                    return;
                }
            } else {
                userService.editUser(user);
                response.sendRedirect(request.getContextPath() + "/admin");
                return;
            }
        }
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("id");
        String url = "/WEB-INF/views/editUser.jsp";
        if (userId != null) {
            long id = Long.valueOf(userId);
            User user = userService.getUserById(id);
            if (user != null) {
                request.setAttribute("user", user);
                request.getServletContext().getRequestDispatcher(url).forward(request, response);
                return;
            }
        }
        request.getServletContext().getRequestDispatcher("/WEB-INF/views/404.jsp").forward(request, response);
    }
}
