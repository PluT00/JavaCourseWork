package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Flight {
    private int id;
    private String source;
    private String destination;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private Plane plane;
    private Company company;
    private boolean isDeparture;

    public Flight(String source, String destination, Timestamp departureTime, Timestamp arrivalTime, Plane plane, Company company, boolean isDeparture) {
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.plane = plane;
        this.company = company;
        this.isDeparture = isDeparture;
    }
}
