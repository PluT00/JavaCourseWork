package services;

import model.User;
import services.db.UserDAO;

import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public List<User> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    public void deleteUser(int userId) {
        userDAO.deleteUser(userId);
    }

    public User getUserById(int userId) {
        return userDAO.getUserById(userId);
    }

    public User getUserByUsername(String username) {
        return userDAO.getUserByUsername(username);
    }
}
