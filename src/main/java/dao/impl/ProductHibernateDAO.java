package dao.impl;

import connectivity.HibernateSession;
import dao.ProductDAO;
import model.Product;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.math.BigInteger;
import java.util.List;

public class ProductHibernateDAO implements ProductDAO {

    @Override
    public List<Product> getAllProduct() {
        Session session = HibernateSession.getSessionFactory().openSession();
        List<Product> productList = (List<Product>) session.createQuery("from Product").list();
        session.close();
        return productList;
    }

    @Override
    public Long addProduct(Product product) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(product);
        transaction.commit();
        Long lastId = ((BigInteger) session.createSQLQuery("SELECT LASTVAL()").uniqueResult()).longValue();
        session.close();
        return lastId;
    }

    @Override
    public Product getProductById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Product product = session.get(Product.class, id);
        session.close();
        return product;
    }

    @Override
    public void updateProduct(Product product) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(product);
        transaction.commit();
        session.close();
    }

    @Override
    public void deleteProductById(Long id) {
        Session session = HibernateSession.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(id);
        transaction.commit();
        session.close();
    }
}
