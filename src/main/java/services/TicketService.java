package services;

import model.Ticket;
import services.db.TicketDAO;

import java.util.List;

public class TicketService {
    private TicketDAO ticketDAO;

    public TicketService(TicketDAO ticketDAO) {
        this.ticketDAO = ticketDAO;
    }

    public void addTicket(Ticket ticket) {
        ticketDAO.addTicket(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    public void updateTicket(Ticket ticket) {
        ticketDAO.updateTicket(ticket);
    }

    public void deleteTicket(int ticketId) {
        ticketDAO.deleteTicket(ticketId);
    }

    public Ticket getTicketById(int ticketId) {
        return ticketDAO.getTicketById(ticketId);
    }

    public Ticket getTicketByName(String name) {
        return ticketDAO.getTicketByName(name);
    }
}
