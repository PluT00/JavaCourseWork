package services;

import model.Plane;
import services.db.PlaneDAO;

import java.util.List;

public class PlaneService {
    private PlaneDAO planeDAO;

    public PlaneService(PlaneDAO planeDAO) {
        this.planeDAO = planeDAO;
    }

    public void addPlane(Plane plane) {
        planeDAO.addPlane(plane);
    }

    public List<Plane> getAllPlanes() {
        return planeDAO.getAllPlanes();
    }

    public void updatePlane(Plane plane) {
        planeDAO.updatePlane(plane);
    }

    public void deletePlane(int planeId) {
        planeDAO.deletePlane(planeId);
    }

    public Plane getPlaneById(int planeId) {
        return planeDAO.getPlaneById(planeId);
    }

    public Plane getPlaneByModel(String planeModel) {
        return planeDAO.getPlaneByModel(planeModel);
    }
}
