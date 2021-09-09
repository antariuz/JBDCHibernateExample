package service;

import model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrder();

    Long addOrder(Order order);

    Order getUserById(Long id);

    void updateOrder(Order order);

    void deleteOrderById(Long id);

}
