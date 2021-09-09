package dao.impl;

import connectivity.JDBC;
import dao.OrderDAO;
import factory.OrderFactory;
import model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderJDBCDAO implements OrderDAO {

    private final Logger LOGGER = LogManager.getLogger(OrderJDBCDAO.class.getName());

    @Override
    public List<Order> getAllOrder() {
        List<Order> list = new ArrayList<>();
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM order")) {
            list = OrderFactory.getInstance().createVOOrderList(resultSet);
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addOrder(Order order) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "INSERT INTO order (user_id, shopping_cart_id, created_date, status) VALUES(?,?,?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getShoppingCartId());
            preparedStatement.setDate(3, (Date) order.getCreatedDate());
            preparedStatement.setString(4, order.getStatus().toString());
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
    public Order getOrderById(Long id) {
        Order order = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM order WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            order = OrderFactory.getInstance().
                    createOrderVO(preparedStatement.executeQuery());
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
        return order;
    }

    @Override
    public void updateOrder(Order order) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "UPDATE order SET user_id = ?, shopping_cart_id = ?, created_date = ?, status = ?" +
                                     "WHERE id = ?")) {
            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setLong(2, order.getShoppingCartId());
            preparedStatement.setDate(3, (Date) order.getCreatedDate());
            preparedStatement.setString(4, order.getStatus().toString());
            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM order WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException | NullPointerException e) {
            LOGGER.error(e);
        }
    }

}
