package service;

import model.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getAllCartItem();

    Long addCartItem(CartItem cartItem);

    CartItem getUserById(Long id);

    void updateCartItem(CartItem cartItem);

    void deleteCartItemById(Long id);

}
