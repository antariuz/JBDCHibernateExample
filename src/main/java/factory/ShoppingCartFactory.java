package factory;

import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ShoppingCartFactory {
    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(ShoppingCartFactory.class.getName());

    ShoppingCartFactory() {
    }

    public static ShoppingCartFactory getInstance() {
        return INSTANCE;
    }

    public ShoppingCart createShoppingCartVO(ResultSet resultSet) {
        ShoppingCart shoppingCart = new ShoppingCart();
        try (resultSet) {
            while (resultSet.next()) {
                shoppingCart.setId(resultSet.getLong("id"));
                shoppingCart.setUserId(resultSet.getLong("user_id"));
                shoppingCart.setCreatedDate(resultSet.getDate("created_date"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return shoppingCart;
    }

    public List<ShoppingCart> createVOShoppingCartList(ResultSet resultSet) {
        List<ShoppingCart> list = new ArrayList<>();
        try (resultSet) {
            while (resultSet.next()) {
                ShoppingCart shoppingCart = new ShoppingCart();
                shoppingCart.setId(resultSet.getLong("id"));
                shoppingCart.setUserId(resultSet.getLong("user_id"));
                shoppingCart.setCreatedDate(resultSet.getDate("created_date"));
                list.add(shoppingCart);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

}
