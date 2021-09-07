package service.impl;

import dao.UserDAO;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getAllUser() {
        return userDAO.getAllUser();
    }

    @Override
    public Long addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public User getUserById(Long id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userDAO.deleteUserByID(id);
    }
}
