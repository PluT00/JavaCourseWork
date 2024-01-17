package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plane {
    private int id;
    private String manufacturer;
    private String model;
    private int capacity;

    public Plane(String manufacturer, String model, int capacity) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "id=" + id +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
