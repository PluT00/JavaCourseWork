package services.db;

import model.Flight;
import services.CompanyService;
import services.PlaneService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightDAO {
    private DatabaseService databaseService;
    private PlaneService planeService;
    private CompanyService companyService = new CompanyService(new CompanyDAO(new DatabaseService()));

    public FlightDAO(DatabaseService databaseService) {
        this.databaseService = databaseService;
        this.planeService = new PlaneService(new PlaneDAO(databaseService));
    }

    public void addFlight(Flight flight) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO flights (source, destination, departure_time, arrival_time, plane, company, is_departure) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, flight.getSource());
                preparedStatement.setString(2, flight.getDestination());
                preparedStatement.setTimestamp(3, flight.getDepartureTime());
                preparedStatement.setTimestamp(4, flight.getArrivalTime());
                preparedStatement.setString(5, flight.getPlane().getModel());
                preparedStatement.setInt(6, flight.getCompany().getId());
                preparedStatement.setBoolean(7, flight.isDeparture());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM flights";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Flight flight = new Flight();
                        flight.setId(resultSet.getInt("id"));
                        flight.setSource(resultSet.getString("source"));
                        flight.setDestination(resultSet.getString("destination"));
                        flight.setDepartureTime(resultSet.getTimestamp("departure_time"));
                        flight.setArrivalTime(resultSet.getTimestamp("arrival_time"));
                        flight.setPlane(planeService.getPlaneByModel(resultSet.getString("plane")));
                        flight.setCompany(companyService.getCompanyById(resultSet.getInt("company")));
                        flight.setDeparture(resultSet.getBoolean("is_departure"));
                        flights.add(flight);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flights;
    }

    public void updateFlight(Flight flight) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "UPDATE flights SET source=?, destination=?, departure_time=?, arrival_time=?, plane=?, company=?, is_departure=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, flight.getSource());
                preparedStatement.setString(2, flight.getDestination());
                preparedStatement.setTimestamp(3, flight.getDepartureTime());
                preparedStatement.setTimestamp(4, flight.getArrivalTime());
                preparedStatement.setString(5, flight.getPlane().getModel());
                preparedStatement.setInt(6, flight.getCompany().getId());
                preparedStatement.setBoolean(7, flight.isDeparture());
                preparedStatement.setInt(8, flight.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(int flightId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM flights WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, flightId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Flight getFlightById(int flightId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM flights WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, flightId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Flight flight = new Flight();
                        flight.setId(resultSet.getInt("id"));
                        flight.setSource(resultSet.getString("source"));
                        flight.setDestination(resultSet.getString("destination"));
                        flight.setDepartureTime(resultSet.getTimestamp("departure_time"));
                        flight.setArrivalTime(resultSet.getTimestamp("arrival_time"));
                        flight.setPlane(planeService.getPlaneByModel(resultSet.getString("plane")));
                        flight.setCompany(companyService.getCompanyById(resultSet.getInt("company")));
                        flight.setDeparture(resultSet.getBoolean("is_departure"));
                        return flight;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

