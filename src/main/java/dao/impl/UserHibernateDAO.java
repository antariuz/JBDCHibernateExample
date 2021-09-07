package dao.impl;

import connectivity.HibernateSession;
import dao.DAO;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class UserHibernateDAO implements DAO<User> {

    @Override
    public List<User> getAllUser() {
        Session session = HibernateSession.getSessionFactory().openSession();
        List userList = session.createQuery("From User").list();
        session.close();
        return userList;
    }

    @Override
    public Long addUser(User user) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        Long lastId = ((BigInteger) session.createSQLQuery("SELECT LASTVAL()").uniqueResult()).longValue();
        session.close();
        return lastId;
    }

    @Override
    public User getUserByID(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        User user = session.get(User.class, id);
        session.close();
        return user;
    }

    @Override
    public void updateUser(User user) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteUserByID(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }
}
