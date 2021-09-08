package dao;

import java.util.List;

public interface ShoppingCartDAO {

    List<ShoppingCartDAO> getAllShoppingCart();

    Long addShoppingCart(ShoppingCartDAO shoppingCartDAO);

    ShoppingCartDAO getShoppingCartById(Long id);

    void updateShoppingCart(ShoppingCartDAO shoppingCartDAO);

    void deleteShoppingCartById(Long id);

}
