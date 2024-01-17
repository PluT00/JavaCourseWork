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

@WebServlet(name = "FlightListServlet", urlPatterns = "/flights")
public class FlightListServlet extends HttpServlet {
    private final FlightService flightService = new FlightService(new FlightDAO(new DatabaseService()));
    private final PlaneService planeService = new PlaneService(new PlaneDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            List<Flight> flights = flightService.getAllFlights();
            List<Plane> planes = planeService.getAllPlanes();
            request.setAttribute("flights", flights);
            request.setAttribute("planes", planes);
            request.getRequestDispatcher("pages/flightList.jsp").forward(request, response);
        } else {
            response.sendRedirect("/signin");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            String source = request.getParameter("source");
            String destination = request.getParameter("destination");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            Timestamp departureTime = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("departureTime"), formatter));
            Timestamp arrivalTime = Timestamp.valueOf(LocalDateTime.parse(request.getParameter("arrivalTime"), formatter));
            boolean isDeparture = request.getParameter("isDeparture") != null;
            Plane plane = planeService.getPlaneByModel(request.getParameter("planeModel"));
            Flight newFlight = new Flight(source, destination, departureTime, arrivalTime, plane, isDeparture);
            flightService.addFlight(newFlight);

            response.sendRedirect(request.getContextPath() + "/flights");
        } else {
            response.sendRedirect("/signin");
        }
    }
}

