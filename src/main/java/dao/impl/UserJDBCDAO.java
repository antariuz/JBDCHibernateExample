package dao.impl;

import connectivity.JDBC;
import dao.UserDAO;
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
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM public.user")) {
            list = UserFactory.getInstance().createVOUserList(resultSet);
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
                     connection.prepareStatement("INSERT INTO public.user (name, surname, email) VALUES(?,?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM public.user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            user = UserFactory.getInstance().createUserVO(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE public.user SET name = ?, surname = ?, email = ? WHERE id = ?")) {
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
    public void deleteUserById(Long id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("DELETE FROM public.user WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
