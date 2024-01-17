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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/flight_details")
public class FlightDetailsServlet extends HttpServlet {
    private final FlightService flightService = new FlightService(new FlightDAO(new DatabaseService()));
    private final PlaneService planeService = new PlaneService(new PlaneDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Flight flight = flightService.getFlightById(id);
                List<Plane> planes = planeService.getAllPlanes();
                request.setAttribute("flight", flight);
                request.setAttribute("planes", planes);
                request.getRequestDispatcher("pages/flightDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/flights");
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
                    String source = request.getParameter("source");
                    String destination = request.getParameter("destination");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                    Timestamp departureTime = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("departureTime"), formatter));
                    Timestamp arrivalTime = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("arrivalTime"), formatter));
                    boolean isDeparture = request.getParameter("isDeparture") != null;
                    Plane plane = planeService.getPlaneByModel(request.getParameter("planeModel"));
                    Flight newFlight = new Flight(id, source, destination, departureTime, arrivalTime, plane, isDeparture);
                    flightService.updateFlight(newFlight);
                } else if ("delete".equals(action)) {
                    flightService.deleteFlight(id);
                }
                response.sendRedirect(request.getContextPath() + "/flights");
            } else {
                response.sendRedirect(request.getContextPath() + "/flights");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }
}