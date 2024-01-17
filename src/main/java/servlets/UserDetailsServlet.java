package servlets;

import model.Plane;
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


@WebServlet("/user_details")
public class UserDetailsServlet extends HttpServlet {
    private final UserService userService = new UserService(new UserDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                User user = userService.getUserById(id);
                request.setAttribute("user", user);
                request.getRequestDispatcher("pages/usersDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/users");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                User oldUser = userService.getUserById(Integer.parseInt(request.getParameter("id")));
                int id = Integer.parseInt(request.getParameter("id"));
                String action = request.getParameter("action");
                if ("update".equals(action)) {
                    String password = request.getParameter("password");
                    User newUser = new User(id, oldUser.getUsername(), password);
                    userService.updateUser(newUser);
                } else if ("delete".equals(action)) {
                    userService.deleteUser(id);
                }
                response.sendRedirect(request.getContextPath() + "/users");
            } else {
                response.sendRedirect(request.getContextPath() + "/users");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }
}