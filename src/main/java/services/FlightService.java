package services;

import model.Flight;
import services.db.FlightDAO;

import java.util.List;

public class FlightService {
    private FlightDAO flightDAO;

    public FlightService(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }

    public void addFlight(Flight flight) {
        flightDAO.addFlight(flight);
    }

    public List<Flight> getAllFlights() {
        return flightDAO.getAllFlights();
    }

    public void updateFlight(Flight flight) {
        flightDAO.updateFlight(flight);
    }

    public void deleteFlight(int flightId) {
        flightDAO.deleteFlight(flightId);
    }

    public Flight getFlightById(int flightId) {
        return flightDAO.getFlightById(flightId);
    }
}

