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

@WebServlet(name = "AddServlet", urlPatterns = "/admin/add")
public class AddServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        this.userService = UserServiceImpl.getInstance();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userAge = request.getParameter("age");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (userAge != null && firstName != null && lastName != null
                && email != null && password != null && role != null) {
            User user = new User(firstName.trim(), lastName.trim(), Integer.valueOf(userAge), email, password, role);
            if (userService.getUserByEmail(email) == null) {
                userService.addNewUser(user);
            }
        }
        response.sendRedirect(request.getContextPath() + "/admin"); //Редирект на "главную" в любом случае
    }
}