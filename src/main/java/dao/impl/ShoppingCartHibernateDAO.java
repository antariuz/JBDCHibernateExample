package dao.impl;

import connectivity.HibernateSession;
import dao.ShoppingCartDAO;
import model.ShoppingCart;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class ShoppingCartHibernateDAO implements ShoppingCartDAO {

    @Override
    public List<ShoppingCart> getAllShoppingCart() {
        Session session = HibernateSession.getSessionFactory().openSession();
        List<ShoppingCart> shoppingCartList =
                (List<ShoppingCart>) session.createQuery("FROM ShoppingCart").list();
        session.close();
        return shoppingCartList;
    }

    @Override
    public Long addShoppingCart(ShoppingCart shoppingCart) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(shoppingCart);
        transaction.commit();
        Long lastId = ((BigInteger) session.createSQLQuery("SELECT LASTVAL()").uniqueResult()).longValue();
        session.close();
        return lastId;
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        ShoppingCart shoppingCart = session.get(ShoppingCart.class, id);
        session.close();
        return shoppingCart;
    }

    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(shoppingCart);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteShoppingCartById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }
}
