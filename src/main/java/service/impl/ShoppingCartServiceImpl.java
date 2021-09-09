package service.impl;

import dao.ShoppingCartDAO;
import dao.UserDAO;
import model.ShoppingCart;
import model.User;
import service.ShoppingCartService;
import service.UserService;

import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDAO shoppingCartDAO;

    public ShoppingCartServiceImpl(ShoppingCartDAO shoppingCartDAO) {
        this.shoppingCartDAO = shoppingCartDAO;
    }

    @Override
    public List<ShoppingCart> getAllShoppingCart() {
        return shoppingCartDAO.getAllShoppingCart();
    }

    @Override
    public Long addShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartDAO.addShoppingCart(shoppingCart);
    }

    @Override
    public ShoppingCart getUserById(Long id) {
        return shoppingCartDAO.getShoppingCartById(id);
    }

    @Override
    public void updateShoppingCart(ShoppingCart shoppingCart) {
        shoppingCartDAO.updateShoppingCart(shoppingCart);
    }

    @Override
    public void deleteShoppingCartById(Long id) {
        shoppingCartDAO.deleteShoppingCartById(id);
    }
}
