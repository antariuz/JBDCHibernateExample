package dao.impl;

import connectivity.HibernateSession;
import dao.OrderDAO;
import model.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class OrderHibernateDAO implements OrderDAO {

    @Override
    public List<Order> getAllOrder() {
        Session session = HibernateSession.getSessionFactory().openSession();
        List<Order> orderList =
                (List<Order>) session.createQuery("FROM Order").list();
        session.close();
        return orderList;
    }

    @Override
    public Long addOrder(Order order) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(order);
        transaction.commit();
        Long lastId = ((BigInteger) session.createSQLQuery("SELECT LASTVAL()").uniqueResult()).longValue();
        session.close();
        return lastId;
    }

    @Override
    public Order getOrderById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Order order = session.get(Order.class, id);
        session.close();
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(order);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteOrderById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }

}
