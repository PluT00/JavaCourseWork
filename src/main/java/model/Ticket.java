package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ticket {
    private int id;
    private User user;
    private Flight flight;

    public Ticket(User user, Flight flight) {
        this.user = user;
        this.flight = flight;
    }
}
