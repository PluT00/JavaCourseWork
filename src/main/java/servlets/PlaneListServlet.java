package servlets;

import model.Flight;
import model.Plane;
import services.FlightService;
import services.PlaneService;
import services.db.DatabaseService;
import services.db.FlightDAO;
import services.db.PlaneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "PlaneListServlet", urlPatterns = "/planes")
public class PlaneListServlet extends HttpServlet {
    private final PlaneService planeService = new PlaneService(new PlaneDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            List<Plane> planes = planeService.getAllPlanes();
            request.setAttribute("planes", planes);
            request.getRequestDispatcher("pages/planesList.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String manufacturer = request.getParameter("manufacturer");
            String model = request.getParameter("model");
            int capacity = Integer.parseInt(request.getParameter("capacity"));
            Plane newPlane = new Plane(manufacturer, model, capacity);
            planeService.addPlane(newPlane);

            response.sendRedirect(request.getContextPath() + "/planes");
        } else {
            response.sendRedirect("/signin");
        }
    }
}

