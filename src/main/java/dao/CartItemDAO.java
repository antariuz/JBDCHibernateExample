package dao;

import model.CartItem;

import java.util.List;

public interface CartItemDAO {

    List<CartItem> getAllCartItem();

    Long addCartItem(CartItem cartItem);

    CartItem getCartItemById(Long id);

    void updateCartItem(CartItem cartItem);

    void deleteCartItemById(Long id);

}
