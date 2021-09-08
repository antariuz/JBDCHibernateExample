package factory;

import model.ShoppingCart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public enum ShoppingCartFactory {
    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(ShoppingCartFactory.class.getName());

    ShoppingCartFactory() {
    }

    public final ShoppingCartFactory getInstance(){
        return INSTANCE;
    }

    public ShoppingCartFactory createShoppingCartVO(ResultSet resultSet){
        ShoppingCart shoppingCart = new ShoppingCart();
        try(resultSet){
            while (resultSet.next()){
                shoppingCart.setId(resultSet.getLong("shopping_cart_id"));
//                shoppingCart.setUser(resultSet.getLong("user_id"));
                shoppingCart.setCreatedDate(resultSet.getDate("created_date"));
            }
        } catch (SQLException e){
            LOGGER.error(e);
        }


        return null;
    }

}
