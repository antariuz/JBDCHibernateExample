package service.impl;

import dao.UserDAO;
import dao.impl.UserJDBCDAO;
import model.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
    }

    @Override
    public List<User> getAllUser() {
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
        return userJDBCDAO.getAllUser();
    }

    @Override
    public Long addUser(User user) {
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
        return userJDBCDAO.addUser(user);
    }

    @Override
    public User getUserById(Long id) {
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
        return userJDBCDAO.getUserByID(id);
    }

    @Override
    public void updateUser(User user) {
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
        userJDBCDAO.updateUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        UserJDBCDAO userJDBCDAO = new UserJDBCDAO();
        userJDBCDAO.deleteUserByID(id);
    }
}