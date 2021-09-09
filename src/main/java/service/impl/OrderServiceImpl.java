package service.impl;

import dao.OrderDAO;
import model.Order;
import service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getAllOrder() {
        return orderDAO.getAllOrder();
    }

    @Override
    public Long addOrder(Order order) {
        return orderDAO.addOrder(order);
    }

    @Override
    public Order getUserById(Long id) {
        return orderDAO.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrderById(Long id) {
        orderDAO.deleteOrderById(id);
    }
}
