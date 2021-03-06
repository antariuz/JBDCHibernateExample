package dao;

import model.ShoppingCart;

import java.util.List;

public interface ShoppingCartDAO {

    List<ShoppingCart> getAllShoppingCart();

    Long addShoppingCart(ShoppingCart shoppingCart);

    ShoppingCart getShoppingCartById(Long id);

    void updateShoppingCart(ShoppingCart shoppingCart);

    void deleteShoppingCartById(Long id);

}
