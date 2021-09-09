package service.impl;

import dao.CartItemDAO;
import model.CartItem;
import service.CartItemService;

import java.util.List;

public class CartItemServiceImpl implements CartItemService {

    private final CartItemDAO cartItemDAO;

    public CartItemServiceImpl(CartItemDAO cartItemDAO) {
        this.cartItemDAO = cartItemDAO;
    }

    @Override
    public List<CartItem> getAllCartItem() {
        return cartItemDAO.getAllCartItem();
    }

    @Override
    public Long addCartItem(CartItem cartItem) {
        return cartItemDAO.addCartItem(cartItem);
    }

    @Override
    public CartItem getUserById(Long id) {
        return cartItemDAO.getCartItemById(id);
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        cartItemDAO.updateCartItem(cartItem);
    }

    @Override
    public void deleteCartItemById(Long id) {
        cartItemDAO.deleteCartItemById(id);
    }
}
