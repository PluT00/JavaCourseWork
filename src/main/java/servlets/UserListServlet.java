package servlets;

import model.User;
import services.UserService;
import services.db.DatabaseService;
import services.db.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserListServlet", urlPatterns = "/users")
public class UserListServlet extends HttpServlet {
    UserService userService = new UserService(new UserDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            List<User> users = userService.getAllUsers();
            request.setAttribute("users", users);
            request.setAttribute("username", session.getAttribute("username"));
            request.getRequestDispatcher("pages/usersList.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signin");
        }
    }
}

