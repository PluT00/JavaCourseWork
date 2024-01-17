package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private int id;
    private String source;
    private String destination;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private Plane plane;
    private boolean isDeparture;

    public Flight(String source, String destination, Timestamp departureTime, Timestamp arrivalTime, Plane plane, boolean isDeparture) {
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.plane = plane;
        this.isDeparture = isDeparture;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", plane=" + plane.toString() +
                ", isDeparture=" + isDeparture +
                '}';
    }
}
