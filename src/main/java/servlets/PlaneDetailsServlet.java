package servlets;

import model.Plane;
import services.PlaneService;
import services.db.DatabaseService;
import services.db.PlaneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/plane_details")
public class PlaneDetailsServlet extends HttpServlet {
    private final PlaneService planeService = new PlaneService(new PlaneDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Plane plane = planeService.getPlaneById(id);
                request.setAttribute("plane", plane);
                request.getRequestDispatcher("pages/planeDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/planes");
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
                int id = Integer.parseInt(request.getParameter("id"));
                String action = request.getParameter("action");
                if ("update".equals(action)) {
                    String manufacturer = request.getParameter("manufacturer");
                    String model = request.getParameter("model");
                    int capacity = Integer.parseInt(request.getParameter("capacity"));
                    Plane newPlane = new Plane(id, manufacturer, model, capacity);
                    planeService.updatePlane(newPlane);
                } else if ("delete".equals(action)) {
                    planeService.deletePlane(id);
                }
                response.sendRedirect(request.getContextPath() + "/planes");
            } else {
                response.sendRedirect(request.getContextPath() + "/planes");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }
}