package factory;

import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum UserFactory {
    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(UserFactory.class.getName());

    UserFactory() {
    }

    public static UserFactory getInstance() {
        return INSTANCE;
    }

    public User createUserVO(ResultSet resultSet) {
        User user = new User();
        try (resultSet) {
            while (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }

    public List<User> createVOUserList(ResultSet resultSet) {
        List<User> list = new ArrayList<>();
        try (resultSet) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setEmail(resultSet.getString("email"));
                list.add(user);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }


}
