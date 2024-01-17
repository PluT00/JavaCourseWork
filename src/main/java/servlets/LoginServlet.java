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
import java.util.Objects;

@WebServlet(name = "LoginServlet", urlPatterns = "/signin")
public class LoginServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() {
        this.userService = new UserService(new UserDAO(new DatabaseService()));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("/home");
        } else {
            request.getRequestDispatcher("pages/signin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session_check = request.getSession(false);
        if (session_check != null && session_check.getAttribute("username") != null) {
            response.sendRedirect("/home");
        } else {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.getUserByUsername(username);

            if (user != null && Objects.equals(password, user.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("username", user.getUsername());
                response.sendRedirect("/home");
            } else {
                response.sendRedirect("/signin?error=true");
            }
        }
    }
}