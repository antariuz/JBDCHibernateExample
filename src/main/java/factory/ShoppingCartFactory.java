package factory;

import model.ShoppingCart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ShoppingCartFactory {
    INSTANCE;

    ShoppingCartFactory() {
    }

    public static ShoppingCartFactory getInstance() {
        return INSTANCE;
    }

    public ShoppingCart createShoppingCartVO(ResultSet resultSet) throws SQLException {
        ShoppingCart shoppingCart = new ShoppingCart();
        if (resultSet.next()) {
            shoppingCart.setId(resultSet.getLong("id"));
            shoppingCart.setUserId(resultSet.getLong("user_id"));
            shoppingCart.setCreatedDate(resultSet.getDate("created_date"));
        }
        return shoppingCart;
    }

    public List<ShoppingCart> createVOShoppingCartList(ResultSet resultSet) throws SQLException {
        List<ShoppingCart> list = new ArrayList<>();
        while (resultSet.next()) {
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setId(resultSet.getLong("id"));
            shoppingCart.setUserId(resultSet.getLong("user_id"));
            shoppingCart.setCreatedDate(resultSet.getDate("created_date"));
            list.add(shoppingCart);
        }
        return list;
    }

}
