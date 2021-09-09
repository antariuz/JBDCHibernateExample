package dao;

import model.Order;

import java.util.List;

public interface OrderDAO {

    List<Order> getAllOrder();

    Long addOrder(Order order);

    Order getOrderById(Long id);

    void updateOrder(Order order);

    void deleteOrderById(Long id);

}
