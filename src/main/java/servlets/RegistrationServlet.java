package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import services.UserService;
import services.db.DatabaseService;
import services.db.UserDAO;

import java.io.IOException;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/signup")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = new UserService(new UserDAO(new DatabaseService()));

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("/home");
        } else {
            request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("/home");
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (userService.getUserByUsername(username) != null) {
                request.setAttribute("error", "User with this username already exists");
                request.getRequestDispatcher("pages/signup.jsp").forward(request, response);
            } else {
                User newUser = new User(username, password);
                userService.addUser(newUser);

                response.sendRedirect(request.getContextPath() + "/signin");
            }
        }
    }
}
