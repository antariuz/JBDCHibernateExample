package dao;

import java.util.List;

public interface DAO<T> {

    List<T> getAllUser();

    Long addUser(T t);

    T getUserByID(Long id);

    void updateUser(T t);

    void deleteUserByID(Long id);

}
