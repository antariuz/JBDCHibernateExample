package factory;

import model.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum OrderFactory {
    INSTANCE;

    OrderFactory() {
    }

    public static OrderFactory getInstance() {
        return INSTANCE;
    }

    public Order createOrderVO(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        if (resultSet.next()) {
            order.setId(resultSet.getLong("id"));
            order.setUserId(resultSet.getLong("user_id"));
            order.setShoppingCartId(resultSet.getLong("shopping_cart_id"));
            order.setCreatedDate(resultSet.getDate("created_date"));
            order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
        }
        return order;
    }

    public List<Order> createVOOrderList(ResultSet resultSet) throws SQLException {
        List<Order> list = new ArrayList<>();
        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getLong("id"));
            order.setUserId(resultSet.getLong("user_id"));
            order.setShoppingCartId(resultSet.getLong("shopping_cart_id"));
            order.setCreatedDate(resultSet.getDate("created_date"));
            order.setStatus(Order.Status.valueOf(resultSet.getString("status")));
            list.add(order);
        }
        return list;
    }

}
