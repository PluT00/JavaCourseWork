package services.db;

import model.Flight;
import model.Ticket;
import model.User;
import services.FlightService;
import services.UserService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    private DatabaseService databaseService;
    private UserService userService = new UserService(new UserDAO(new DatabaseService()));
    private FlightService flightService = new FlightService(new FlightDAO(new DatabaseService()));

    public TicketDAO(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void addTicket(Ticket ticket) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO tickets (user_id, flight) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ticket.getUser().getId());
                preparedStatement.setInt(2, ticket.getFlight().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM tickets";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Ticket ticket = new Ticket();
                        ticket.setId(resultSet.getInt("id"));
                        ticket.setUser(userService.getUserById(Integer.parseInt(resultSet.getString("user_id"))));
                        ticket.setFlight(flightService.getFlightById(Integer.parseInt(resultSet.getString("flight"))));
                        tickets.add(ticket);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<Ticket> filterTicketsByUser(User user) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM tickets WHERE user_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, user.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Ticket ticket = new Ticket();
                        ticket.setId(resultSet.getInt("id"));
                        ticket.setUser(userService.getUserById(Integer.parseInt(resultSet.getString("user_id"))));
                        ticket.setFlight(flightService.getFlightById(Integer.parseInt(resultSet.getString("flight"))));
                        tickets.add(ticket);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public List<Ticket> filterTicketsByFlight(Flight flight) {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM tickets WHERE flight=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, flight.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Ticket ticket = new Ticket();
                        ticket.setId(resultSet.getInt("id"));
                        ticket.setUser(userService.getUserById(Integer.parseInt(resultSet.getString("user_id"))));
                        ticket.setFlight(flightService.getFlightById(Integer.parseInt(resultSet.getString("flight"))));
                        tickets.add(ticket);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    public void updateTicket(Ticket ticket) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "UPDATE tickets SET user_id=?, flight=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ticket.getUser().getId());
                preparedStatement.setInt(2, ticket.getFlight().getId());
                preparedStatement.setInt(3, ticket.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteTicket(int ticketId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM tickets WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ticketId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Ticket getTicketById(int ticketId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM tickets WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, ticketId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Ticket ticket = new Ticket();
                        ticket.setId(resultSet.getInt("id"));
                        ticket.setUser(userService.getUserById(Integer.parseInt(resultSet.getString("user_id"))));
                        ticket.setFlight(flightService.getFlightById(Integer.parseInt(resultSet.getString("flight"))));
                        return ticket;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Ticket getTicketByName(String name) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM tickets WHERE name=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Ticket ticket = new Ticket();
                        ticket.setId(resultSet.getInt("id"));
                        ticket.setUser(userService.getUserById(Integer.parseInt(resultSet.getString("user_id"))));
                        ticket.setFlight(flightService.getFlightById(Integer.parseInt(resultSet.getString("flight"))));
                        return ticket;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

