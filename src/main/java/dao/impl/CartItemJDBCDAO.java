package dao.impl;

import connectivity.JDBC;
import dao.CartItemDAO;
import factory.CartItemFactory;
import model.CartItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartItemJDBCDAO implements CartItemDAO {

    private final Logger LOGGER = LogManager.getLogger(CartItemJDBCDAO.class.getName());

    @Override
    public List<CartItem> getAllCartItem() {
        List<CartItem> list = new ArrayList<>();
        try (Connection connection = new JDBC().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM public.cart_item")) {
            list = CartItemFactory.getInstance().createVOCartItemList(resultSet);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

    @Override
    public Long addCartItem(CartItem cartItem) {
        Long id = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "INSERT INTO public.cart_item (quantity, shopping_cart_id, product_id, created_date) VALUES(?,?,?,?)",
                             PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setLong(2, cartItem.getShoppingCartId());
            preparedStatement.setLong(3, cartItem.getProductId());
            preparedStatement.setDate(4, (Date) cartItem.getCreatedDate());
            preparedStatement.execute();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                id = resultSet.next() ? resultSet.getLong(1) : null;
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return id;
    }

    @Override
    public CartItem getCartItemById(Long id) {
        CartItem cartItem = null;
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement("SELECT * FROM public.cart_item WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            cartItem = CartItemFactory.getInstance().
                    createCartItemVO(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return cartItem;
    }

    @Override
    public void updateCartItem(CartItem cartItem) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(
                             "UPDATE public.cart_item SET quantity = ?, shopping_cart_id = ?, product_id = ?, created_date = ? WHERE id = ?")) {
            preparedStatement.setInt(1, cartItem.getQuantity());
            preparedStatement.setLong(2, cartItem.getShoppingCartId());
            preparedStatement.setLong(3, cartItem.getProductId());
            preparedStatement.setDate(4, (Date) cartItem.getCreatedDate());
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void deleteCartItemById(Long id) {
        try (Connection connection = new JDBC().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM public.cart_item WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

}
