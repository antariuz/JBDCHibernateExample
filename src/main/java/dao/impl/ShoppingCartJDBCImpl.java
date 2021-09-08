package dao.impl;

import connectivity.JDBC;
import dao.ShoppingCartDAO;
import factory.ShoppingCartFactory;
import factory.UserFactory;
import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartJDBCImpl implements ShoppingCartDAO {

    private final Logger LOGGER = LogManager.getLogger(ShoppingCartJDBCImpl.class.getName());

    @Override
    public List<ShoppingCartDAO> getAllShoppingCart() {
        List<ShoppingCart> list = new ArrayList<>();
        ShoppingCartFactory shoppingCartFactory = ShoppingCartFactory.getInstance();
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM public.shopping_cart")) {
//            list = userFactory.createVOUserList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    @Override
    public Long addShoppingCart(ShoppingCartDAO shoppingCartDAO) {
        return null;
    }

    @Override
    public ShoppingCartDAO getShoppingCartById(Long id) {
        return null;
    }

    @Override
    public void updateShoppingCart(ShoppingCartDAO shoppingCartDAO) {

    }

    @Override
    public void deleteShoppingCartById(Long id) {

    }
}
