package service;

import model.ShoppingCart;
import model.User;

import java.util.List;

public interface ShoppingCartService {

    List<ShoppingCart> getAllShoppingCart();

    Long addShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getUserById(Long id);

    void updateShoppingCart(ShoppingCart shoppingCart);

    void deleteShoppingCartById(Long id);

}
