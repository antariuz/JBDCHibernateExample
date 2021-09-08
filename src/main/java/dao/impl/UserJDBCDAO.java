package dao.impl;

import dao.UserDAO;
import connectivity.JDBC;
import factory.UserFactory;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJDBCDAO implements UserDAO {

    private final Logger LOGGER = LogManager.getLogger(UserJDBCDAO.class.getName());

    @Override
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        UserFactory userFactory = UserFactory.getInstance();
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM public.user")) {
            list = userFactory.createVOUserList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addUser(User user) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO user (name, surname, email) VALUES(?,?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Override
    public User getUserByID(Long id) {
        User user = null;
        UserFactory userFactory = UserFactory.getInstance();
        try (Connection connection = new JDBC().getConnection();
             Statement statement =
                     connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM user WHERE id ='" + id + "'")) {
            user = userFactory.createUserVO(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("update user set name = ?, surname = ?, email = ? where id = ?")) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setLong(4, user.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteUserByID(Long id) {
        try (Connection connection = new JDBC().getConnection();
             Statement statement =
                     connection.createStatement()) {
            statement.executeQuery("DELETE FROM user WHERE id ='" + id + "'");
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
