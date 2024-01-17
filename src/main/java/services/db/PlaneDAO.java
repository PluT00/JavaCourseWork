package services.db;

import model.Plane;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaneDAO {
    private DatabaseService databaseService;

    public PlaneDAO(DatabaseService databaseService) {
        this.databaseService = databaseService;
    }

    public void addPlane(Plane plane) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "INSERT INTO planes (manufacturer, model, capacity) VALUES (?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, plane.getManufacturer());
                preparedStatement.setString(2, plane.getModel());
                preparedStatement.setInt(3, plane.getCapacity());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Plane> getAllPlanes() {
        List<Plane> planes = new ArrayList<>();
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM planes";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Plane plane = new Plane();
                        plane.setId(resultSet.getInt("id"));
                        plane.setManufacturer(resultSet.getString("manufacturer"));
                        plane.setModel(resultSet.getString("model"));
                        plane.setCapacity(resultSet.getInt("capacity"));
                        planes.add(plane);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planes;
    }

    public void updatePlane(Plane plane) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "UPDATE planes SET manufacturer=?, model=?, capacity=? WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, plane.getManufacturer());
                preparedStatement.setString(2, plane.getModel());
                preparedStatement.setInt(3, plane.getCapacity());
                preparedStatement.setInt(4, plane.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePlane(int planeId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "DELETE FROM planes WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, planeId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plane getPlaneById(int planeId) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM planes WHERE id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setInt(1, planeId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Plane plane = new Plane();
                        plane.setId(resultSet.getInt("id"));
                        plane.setManufacturer(resultSet.getString("manufacturer"));
                        plane.setModel(resultSet.getString("model"));
                        plane.setCapacity(resultSet.getInt("capacity"));
                        return plane;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Plane getPlaneByModel(String planeModel) {
        try (Connection connection = databaseService.getConnection()) {
            String query = "SELECT * FROM planes WHERE model=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, planeModel);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Plane plane = new Plane();
                        plane.setId(resultSet.getInt("id"));
                        plane.setManufacturer(resultSet.getString("manufacturer"));
                        plane.setModel(resultSet.getString("model"));
                        plane.setCapacity(resultSet.getInt("capacity"));
                        return plane;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
