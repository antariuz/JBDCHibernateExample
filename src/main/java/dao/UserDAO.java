package dao;

import model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAllUser();

    Long addUser(User user);

    User getUserByID(Long id);

    void updateUser(User user);

    void deleteUserByID(Long id);

}
