package servlets;

import model.Company;
import model.Flight;
import model.Ticket;
import model.User;
import services.CompanyService;
import services.FlightService;
import services.TicketService;
import services.UserService;
import services.db.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ticket_details")
public class TicketDetailsServlet extends HttpServlet {
    private TicketService ticketService = new TicketService(new TicketDAO(new DatabaseService()));
    private UserService userService = new UserService(new UserDAO(new DatabaseService()));
    private FlightService flightService = new FlightService(new FlightDAO(new DatabaseService()));

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            if (request.getParameter("id") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                List<User> users = userService.getAllUsers();
                List<Flight> flights = flightService.getAllFlights();
                Ticket ticket = ticketService.getTicketById(id);
                request.setAttribute("users", users);
                request.setAttribute("flights", flights);
                request.setAttribute("ticket", ticket);
                request.getRequestDispatcher("pages/ticketDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/tickets");
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
                    User user = userService.getUserByUsername(request.getParameter("user"));
                    Flight flight = flightService.getFlightById(Integer.parseInt(request.getParameter("flight")));
                    Ticket ticket = new Ticket(id, user, flight);
                    ticketService.updateTicket(ticket);
                } else if ("delete".equals(action)) {
                    ticketService.deleteTicket(id);
                }
                response.sendRedirect(request.getContextPath() + "/tickets");
            } else {
                response.sendRedirect(request.getContextPath() + "/tickets");
            }
        } else {
            response.sendRedirect("/signin");
        }
    }
}