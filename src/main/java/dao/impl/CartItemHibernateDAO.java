package dao.impl;

import connectivity.HibernateSession;
import dao.CartItemDAO;
import model.CartItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class CartItemHibernateDAO implements CartItemDAO {

    @Override
    public List<CartItem> getAllCartItem() {
        Session session = HibernateSession.getSessionFactory().openSession();
        List<CartItem> cartItemList =
                (List<CartItem>) session.createQuery("FROM CartItem").list();
        session.close();
        return cartItemList;
    }

    @Override
    public Long addCartItem(CartItem cartItem) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cartItem);
        transaction.commit();
        Long lastId = ((BigInteger) session.createSQLQuery("SELECT LASTVAL()").uniqueResult()).longValue();
        session.close();
        return lastId;
    }

    @Override
    public CartItem getCartItemById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        CartItem cartItem = session.get(CartItem.class, id);
        session.close();
        return cartItem;
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cartItem);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteCartItemById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }

}
