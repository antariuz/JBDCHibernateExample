package factory;

import model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum OrderFactory {
    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(OrderFactory.class.getName());

    OrderFactory() {
    }

    public static OrderFactory getInstance() {
        return INSTANCE;
    }

    public Order createOrderVO(ResultSet resultSet) {
        Order order = new Order();
        try (resultSet) {
            while (resultSet.next()) {
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setShoppingCartId(resultSet.getLong("shopping_cart_id"));
                order.setCreatedDate(resultSet.getDate("created_date"));
                order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return order;
    }

    public List<Order> createVOOrderList(ResultSet resultSet) {
        List<Order> list = new ArrayList<>();
        try (resultSet) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong("id"));
                order.setUserId(resultSet.getLong("user_id"));
                order.setShoppingCartId(resultSet.getLong("shopping_cart_id"));
                order.setCreatedDate(resultSet.getDate("created_date"));
                order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
                list.add(order);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

}
