package factory;

import model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public enum ProductFactory {
    INSTANCE;

    private final Logger LOGGER = LogManager.getLogger(ProductFactory.class.getName());

    ProductFactory() {
    }

    public static ProductFactory getInstance() {
        return INSTANCE;
    }

    public Product createProductVO(ResultSet resultSet) {
        Product product = new Product();
        try (resultSet) {
            while (resultSet.next()) {
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return product;
    }

    public List<Product> createVOProductList(ResultSet resultSet) {
        List<Product> list = new ArrayList<>();
        try (resultSet) {
            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getLong("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getDouble("price"));
                list.add(product);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return list;
    }

}
