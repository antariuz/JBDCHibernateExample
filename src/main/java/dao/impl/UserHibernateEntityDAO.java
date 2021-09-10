package dao.impl;

import dao.UserDAO;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserHibernateEntityDAO implements UserDAO {

    private final Logger LOGGER = LogManager.getLogger(UserHibernateEntityDAO.class.getName());

    @Override
    public List<User> getAllUser() {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA").createEntityManager();
        List<User> Users = new ArrayList<>();
        try {
            entityManager.getTransaction().begin();
            Users = entityManager.createQuery("from User").getResultList();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            LOGGER.error(e);
        } finally {
            entityManager.close();
        }
        return Users;
    }

    @Override
    public Long addUser(User user) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA").createEntityManager();
        Long id = null;
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            id = user.getId();
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            LOGGER.error(e);
        } finally {
            entityManager.close();
        }
        return id;
    }

    @Override
    public User getUserById(Long id) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA").createEntityManager();
        User user = new User();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e) {
            LOGGER.error(e);
        } finally {
            entityManager.close();
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA").createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(user);
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            LOGGER.error(e);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void deleteUserById(Long id) {
        EntityManager entityManager = Persistence.createEntityManagerFactory("JPA").createEntityManager();
        User user;
        try {
            entityManager.getTransaction().begin();
            user = entityManager.find(User.class, id);
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
            entityManager.getTransaction().commit();
        } catch (RuntimeException e) {
            LOGGER.error(e);
        } finally {
            entityManager.close();
        }
    }
}
