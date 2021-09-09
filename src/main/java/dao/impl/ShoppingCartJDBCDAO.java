package dao.impl;

import connectivity.JDBC;
import dao.ShoppingCartDAO;
import factory.ShoppingCartFactory;
import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartJDBCDAO implements ShoppingCartDAO {

    private final Logger LOGGER = LogManager.getLogger(ShoppingCartJDBCDAO.class.getName());

    @Override
    public List<ShoppingCart> getAllShoppingCart() {
        List<ShoppingCart> list = new ArrayList<>();
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM shopping_cart")) {
            list = ShoppingCartFactory.getInstance().createVOShoppingCartList(resultSet);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addShoppingCart(ShoppingCart shoppingCart) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO shopping_cart (user_id, created_date) VALUES(?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.setDate(2, (Date) shoppingCart.getCreatedDate());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Override
    public ShoppingCart getShoppingCartById(Long id) {
        ShoppingCart shoppingCart = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM shopping_cart WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            shoppingCart = ShoppingCartFactory.getInstance().
                    createShoppingCartVO(preparedStatement.executeQuery());
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return shoppingCart;
    }

    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("UPDATE shopping_cart SET user_id = ?, created_date = ? WHERE id = ?")) {
            preparedStatement.setLong(1, shoppingCart.getUserId());
            preparedStatement.setDate(2, (Date) shoppingCart.getCreatedDate());
            preparedStatement.setLong(3, shoppingCart.getId());
            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteShoppingCartById(Long id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM shopping_cart WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
    }
}
