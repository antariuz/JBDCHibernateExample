package factory;

import model.CartItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum CartItemFactory {
    INSTANCE;

    CartItemFactory() {
    }

    public static CartItemFactory getInstance() {
        return INSTANCE;
    }

    public CartItem createCartItemVO(ResultSet resultSet) throws SQLException {
        CartItem cartItem = new CartItem();
        if (resultSet.next()) {
            cartItem.setId(resultSet.getLong("id"));
            cartItem.setQuantity(resultSet.getInt("quantity"));
            cartItem.setShoppingCartId(resultSet.getLong("shopping_cart_id"));
            cartItem.setProductId(resultSet.getLong("product_id"));
            cartItem.setCreatedDate(resultSet.getDate("created_date"));
        }
        return cartItem;
    }

    public List<CartItem> createVOCartItemList(ResultSet resultSet) throws SQLException {
        List<CartItem> list = new ArrayList<>();
        while (resultSet.next()) {
            CartItem cartItem = new CartItem();
            cartItem.setId(resultSet.getLong("id"));
            cartItem.setQuantity(resultSet.getInt("quantity"));
            cartItem.setShoppingCartId(resultSet.getLong("shopping_cart_id"));
            cartItem.setProductId(resultSet.getLong("product_id"));
            cartItem.setCreatedDate(resultSet.getDate("created_date"));
            list.add(cartItem);
        }
        return list;
    }

}
